package com.bootcamp.bc_yahoo_finance.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.bc_yahoo_finance.Holiday.Holiday;
import com.bootcamp.bc_yahoo_finance.Repository.StockRepository;
import com.bootcamp.bc_yahoo_finance.Repository.YahooRepository;
import com.bootcamp.bc_yahoo_finance.entity.Data388forRedis;
import com.bootcamp.bc_yahoo_finance.entity.DataforCach;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc_yahoo_finance.entity.SysData388Entity;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.entity.Mapper.DataCachMapper;
import com.bootcamp.bc_yahoo_finance.entity.Mapper.SysDateMapper;
import com.bootcamp.bc_yahoo_finance.entity.Mapper.YahooStockEnityMapper;
import com.bootcamp.bc_yahoo_finance.infira.NotFoundException;
import com.bootcamp.bc_yahoo_finance.model.Stock;
import com.bootcamp.bc_yahoo_finance.service.KeyService;
import com.bootcamp.bc_yahoo_finance.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class StockServiceImpl implements StockService {
  /*
   * @Value(value = "${api.json-place-holder.domain}") //
   * private String domain;
   * 
   * @Value(value = "${api.json-place-holder.endpoints.stocks}")
   * private String usersEndpoint;
   */
  // Cookies
  @Value(value = "A3=d=AQABBNAuAWYCELwcG-DEkVIHurfOQQm9LeAFEgEBAQGAAmYLZlih8HgB_eMAAA&S=AQAAAhPyHLnpsN-ssQl9DnpoX8A; A1=d=AQABBNAuAWYCELwcG-DEkVIHurfOQQm9LeAFEgEBAQGAAmYLZlih8HgB_eMAAA&S=AQAAAhPyHLnpsN-ssQl9DnpoX8A; A1S=d=AQABBNAuAWYCELwcG-DEkVIHurfOQQm9LeAFEgEBAQGAAmYLZlih8HgB_eMAAA&S=AQAAAhPyHLnpsN-ssQl9DnpoX8A") //
  private String cookies;

  @Autowired
  private Holiday holiday;

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Autowired
  private YahooStockEnityMapper yahooStockEnityMapper;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private YahooRepository yahooRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private KeyService keyService;

  @Autowired
  private SysDateMapper sysDateMapper;

  @Autowired
  private DataCachMapper dataCachMapper;

  @Override
  public void addDate() {

    StockEntity s1 = new StockEntity("0700.HK");
    StockEntity s2 = new StockEntity("0388.HK");
    StockEntity s3 = new StockEntity("0005.HK");
    List<StockEntity> stocksEntities = List.of(s1, s2, s3);
    try {
      stockRepository.saveAll(stocksEntities);
    } catch (DataIntegrityViolationException e) {

    }
  }

  @Override
  public List<StockEntity> getStock() throws JsonProcessingException {

    String stocks = redisTemplate.opsForValue().get("stocks-list");
    if (stocks != null) {

      return List.of(objectMapper.readValue(stocks, StockEntity[].class));
    } else {
      List<StockEntity> stocksEntities = stockRepository.findAll();
      String json = objectMapper.writeValueAsString(stocksEntities);
      this.redisTemplate.opsForValue().set("stocks-list", json);

      return stocksEntities;
    }
  }

  @Override
  public List<YahooStockEntity> get5mindate(String symbol, String type) throws JsonProcessingException {
    LocalDateTime current = LocalDateTime.now();
    LocalTime targetTime = LocalTime.of(8, 55);
    LocalDateTime targetDateTime = LocalDateTime.of(current.toLocalDate(), targetTime); // take time
    LocalDateTime searchTime = null;

    List<YahooStockEntity> AllData = new ArrayList<>();

    String allRedisDate = redisTemplate.opsForValue().get("5mins");

    if (allRedisDate != null) { // Redis find data
      AllData = Arrays.asList(objectMapper.readValue(allRedisDate, YahooStockEntity[].class));

    } else { // if Redis can't find, entity set
      List<YahooStockEntity> yahooStockEntity = yahooRepository.findAll();
      String json = objectMapper.writeValueAsString(yahooStockEntity);
      this.redisTemplate.opsForValue().set("5mins", json);
      AllData = yahooStockEntity;
    }

    if (current.isBefore(targetDateTime)) {
      LocalDate workingday = holiday.getPreviousWorkingDay(current.toLocalDate());
      LocalDateTime workingdaytime = workingday.atStartOfDay();
      searchTime = workingdaytime;
    } else if (current.isAfter(targetDateTime) && !(holiday.isHoliday(current.toLocalDate()))) {
      searchTime = current;

    } else if (holiday.isHoliday(current.toLocalDate())) {
      searchTime = holiday.getPreviousWorkingDay(current.toLocalDate()).atStartOfDay();

    }
    // if Hoilday search data
    LocalDate seacrhDate = searchTime.toLocalDate();

    List<YahooStockEntity> targetData = AllData.stream()
        .filter(e -> e.getSymbol().equals(symbol) &&
            e.getType().equals(type) && e.getApiDatetime().toLocalDate().equals(seacrhDate))
        .collect(Collectors.toList());

    return targetData;
  }
  /*
   * //stocks = restTemplate.getForObject(url, StockEnity[].class);
   * HttpHeaders headers = new HttpHeaders();
   * headers.add("Cookie", cookies);
   * HttpEntity<String> httpEntity = new HttpEntity<>(headers);
   * 
   * String url =
   * "https://query1.finance.yahoo.com/v7/finance/quote?symbols=0388.HK&crumb=0LRtuCtkgx.";
   * //接收API 要跟對 JSON -> stock[].class
   * Stock stock = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
   * Stock.class).getBody();
   * //mapper Stockentity xxx = xxxx
   * redisHelper.set("STOCK-LIST", stock, Duration.ofMinutes(5L));
   * return Arrays.asList(stocks);
   * }
   */

  @Override // call API database save date
  public void update5mindata() {
    String key = keyService.hardCodeCrumb();

    RestTemplate resTemplate = new RestTemplate();

    String url388 = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=0388.HK&crumb=" + key;

    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", cookies);
    HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

    ResponseEntity<Stock> response1 = resTemplate.exchange(url388, HttpMethod.GET, httpEntity, Stock.class);

    YahooStockEntity yahooStockEnity388 = yahooStockEnityMapper.map5M(response1.getBody());

    yahooRepository.save(yahooStockEnity388);

    redisTemplate.delete("5min");
    redisTemplate.delete("5MIN-0388.HK");

  }
  @Override
  public void cleanAllSysDate(){
    redisTemplate.delete("sysdata388");
  }

  @Override
  public SysData388Entity getSysDate388(String symbol) throws JsonProcessingException {
    String sysData388 = redisTemplate.opsForValue().get("SYSDATE-0388.HK");

    if (sysData388 != null) {
      SysData388Entity sysData388Entity = objectMapper.readValue(sysData388, SysData388Entity.class);
      return sysData388Entity;
    } else {
      YahooStockEntity yahooStockEntity = yahooRepository.getLatestDataBySymbol(symbol);

      SysData388Entity sysData388Entity = sysDateMapper.map388(yahooStockEntity);
      String json = objectMapper.writeValueAsString(sysData388Entity);

      redisTemplate.opsForValue().set("SYSDATE-0388.HK", json, Duration.ofMinutes(5));

      return sysData388Entity;
    }
  }

  @Override
  public Data388forRedis getData388forRedis() throws JsonProcessingException{
    YahooStockEntity datad1 = yahooRepository.findLatestData().orElseThrow(() ->new NotFoundException());

/*     public StockListDto getStockList() {
      String[] response = redisHelper.get(keyStockList, String[].class);
      if (response != null) {
        return stockListDtoMapper.map(response);
      }
      List<StockEntity> dbList = stockRepository.findAll();
      setStockList(dbList);
      return stockListDtoMapper
          .map(redisHelper.get(keyStockList, String[].class));
    } */

    String sysdata = datad1.getMarketTime().toLocalDate().toString();

    String data388 = redisTemplate.opsForValue().get("5MIN-0388.HK");

    if (data388 != null){
      Data388forRedis data388forRedis = objectMapper.readValue(data388, Data388forRedis.class);
      return data388forRedis;
    }else{
      List<YahooStockEntity> yahooStockEntities = yahooRepository.findAll()
      .stream().filter(e ->e.getSymbol().equals("0388.HK")
      && e.getType().equals("5M") && e.getMarketTime().toLocalDate().toString().equals(sysdata))
      .collect(Collectors.toList());

      List<DataforCach> dataforCach = yahooStockEntities.stream()
      .map(e ->  dataCachMapper.map(e)).collect(Collectors.toList());

      Data388forRedis data388forRedis = new Data388forRedis(dataforCach);
      String json = objectMapper.writeValueAsString(data388forRedis);

      redisTemplate.opsForValue().set("5MIN-0388.HK", json,Duration.ofMinutes(30));
      return data388forRedis;

}
  }
  public void updateDailydata(){String key = keyService.hardCodeCrumb();

    RestTemplate r1 = new RestTemplate();
    String url388 = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=0388.HK&crumb=" + key;

    HttpHeaders headers = new HttpHeaders();
    headers.set("Cookie", cookies);
    HttpEntity<Void> entity = new HttpEntity<>(headers);

    ResponseEntity<Stock> response1 = r1.exchange(url388, HttpMethod.GET, entity, Stock.class);

    YahooStockEntity yahooStockEntity388 = yahooStockEnityMapper.mapdaily(response1.getBody());

    yahooRepository.save(yahooStockEntity388);


    redisTemplate.delete("5min");
    redisTemplate.delete("5MIN-0388.HK");


  }
}