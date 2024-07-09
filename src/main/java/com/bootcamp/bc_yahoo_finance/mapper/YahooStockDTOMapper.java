package com.bootcamp.bc_yahoo_finance.mapper;

import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.dto.YahooStockDTO;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

@Component
public class YahooStockDTOMapper {
  public YahooStockDTO map(YahooStockEntity y1){
    return YahooStockDTO.builder()
    .regularMarketTime(y1.getRegularMarketTime())
    .MarketTime(y1.getMarketTime())
    .regularMarketChangePercent(y1.getRegularMarketChangePercent())
    .regularMarketPrice(y1.getRegularMarketPrice())
    .symbol(y1.getSymbol())
    .MarketTime(y1.getMarketTime())
    .apiDatetime(y1.getApiDatetime())
    .ask(y1.getAsk())
    .askSize(y1.getAskSize())
    .bid(y1.getBid())
    .bidSize(y1.getBidSize())
    .type(y1.getType()).build();
  }
  
}