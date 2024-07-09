package com.bootcamp.bc_yahoo_finance.entity.Mapper;

import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.entity.SysData388Entity;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;



@Component
public class SysDateMapper {
  public SysData388Entity map388(YahooStockEntity y1){
    return SysData388Entity.builder()
    .sysDate(y1.getMarketTime().toLocalDate().toString())
    .build();

  }
  
}