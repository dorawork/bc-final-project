package com.bootcamp.bc_yahoo_finance.mapper;

import java.util.Collections;

import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.dto.FiveMin388DTO;
import com.bootcamp.bc_yahoo_finance.dto.FiveMin388DTO.FiveMindata.Data388Dto;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;

@Component
public class FiveMin388DTOMapper { //map data
  public FiveMin388DTO map(YahooStockEntity s1){

    Data388Dto data388Dto = Data388Dto.builder()//
    .symbol(s1.getSymbol())
    .marketTime(s1.getMarketTime().toString())
    .regularMarketUnix(s1.getRegularMarketTime())
    .regularMarketPrice(s1.getRegularMarketPrice())
    .regularMarketChangePercent(s1.getRegularMarketChangePercent())//
    .build();

    FiveMin388DTO.FiveMindata fiveMindata = FiveMin388DTO.FiveMindata.builder()
    .data388Dto(Collections.singletonList(data388Dto))
    .build();

return FiveMin388DTO.builder()
    .fiveMin388(fiveMindata)
    .build();

  }
}
