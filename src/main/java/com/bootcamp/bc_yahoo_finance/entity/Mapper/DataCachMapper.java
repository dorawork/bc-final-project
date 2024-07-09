package com.bootcamp.bc_yahoo_finance.entity.Mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.entity.DataforCach;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;


@Component
public class DataCachMapper {
  public DataforCach map(YahooStockEntity y1){
    return DataforCach.builder()
    .symbol(y1.getSymbol())
    .marketTime(y1.getMarketTime().toString())
    .regularMarketUnix(y1.getRegularMarketTime())
    .regularMarketChangePercent(y1.getRegularMarketChangePercent())
    .regularMarketPrice(y1.getRegularMarketPrice())
    .build();
  }
}