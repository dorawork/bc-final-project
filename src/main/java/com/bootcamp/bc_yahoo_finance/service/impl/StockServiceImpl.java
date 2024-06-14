package com.bootcamp.bc_yahoo_finance.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.bc_yahoo_finance.Repository.StockRepository;
import com.bootcamp.bc_yahoo_finance.entity.StockEnity;
import com.bootcamp.bc_yahoo_finance.model.Stock;
import com.bootcamp.bc_yahoo_finance.service.StockSerivce;


@Service
public class StockServiceImpl implements StockSerivce{

  @Value (value = "${api.json-place-holder.domain}")//
  private String domain;

  @Value (value =  "${api.json-place-holder.endpoints.stocks}")
  private String usersEndpoint;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockRepository stockRepository;

  @Override
  public Stock getStock(){
  Stock stock = restTemplate.getForObject("query1.finance.yahoo.com/v7/finance", Stock.class);
  return stock;
  }

  @Override
  public StockEnity save(StockEnity stock){
    return stockRepository.save(stock);
  }
}
