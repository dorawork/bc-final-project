package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.bc_yahoo_finance.controller.StockOperation;
import com.bootcamp.bc_yahoo_finance.dto.FiveMin388DTO;
import com.bootcamp.bc_yahoo_finance.dto.FiveMindataMapper;
import com.bootcamp.bc_yahoo_finance.dto.StockDTO;
import com.bootcamp.bc_yahoo_finance.dto.SysData388DTO;
import com.bootcamp.bc_yahoo_finance.dto.SysData388DTOMapper;
import com.bootcamp.bc_yahoo_finance.dto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.mapper.FiveMin388DTOMapper;
import com.bootcamp.bc_yahoo_finance.mapper.StockDTOMapper;
import com.bootcamp.bc_yahoo_finance.mapper.YahooStockDTOMapper;
import com.bootcamp.bc_yahoo_finance.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = "/v1")
public class StocksController implements StockOperation {

  @Autowired
  private StockService stockSerivce;

  @Autowired
  private StockDTOMapper stockDTOMapper;

  @Autowired
  private YahooStockDTOMapper yahooStockDTOMapper;
  
  @Autowired
  private SysData388DTOMapper sysData388DTOMapper;

  @Autowired
  private FiveMindataMapper fiveMindataMapper;

  @Override
  public StockDTO getStock() throws JsonProcessingException {
    return stockDTOMapper.map(stockSerivce.getStock());
  }

  @Override //update database
  public void updateStock() throws JsonProcessingException{
    stockSerivce.update5mindata();
  }

  @Override
  public List<YahooStockDTO> getYahooStockDTOs(@RequestParam String symbol, @RequestParam String type) throws JsonProcessingException {
      return stockSerivce.get5mindate(symbol, type).stream()
              .map(e-> yahooStockDTOMapper.map(e))
              .collect(Collectors.toList());
  }

  @Override
  public SysData388DTO getSysDate388() throws JsonProcessingException{
    String symbol = "0388.HK";
    return sysData388DTOMapper.map(stockSerivce.getSysDate388(symbol));
  }

  @Override
  public FiveMin388DTO get388Data() throws JsonProcessingException{
    return new FiveMin388DTO(fiveMindataMapper.map(stockSerivce.getData388forRedis()));
}
}
  
