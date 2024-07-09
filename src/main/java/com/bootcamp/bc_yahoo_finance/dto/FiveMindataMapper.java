package com.bootcamp.bc_yahoo_finance.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.dto.FiveMin388DTO.FiveMindata;
import com.bootcamp.bc_yahoo_finance.dto.FiveMin388DTO.FiveMindata.Data388Dto;
import com.bootcamp.bc_yahoo_finance.entity.Data388forRedis;
import com.bootcamp.bc_yahoo_finance.entity.DataforCach;

@Component
public class FiveMindataMapper {
  public Data388Dto map(DataforCach D1){
    return Data388Dto.builder().marketTime(D1.getMarketTime())//
    .regularMarketChangePercent(D1.getRegularMarketChangePercent())//
    .regularMarketPrice(D1.getRegularMarketPrice())//
    .regularMarketUnix(D1.getRegularMarketUnix())//
    .symbol(D1.getSymbol()).build();
  }
  public FiveMindata map(Data388forRedis D1){
    List<Data388Dto> data388Dto = D1.getDataforCach().stream()
         .map(e->this.map(e)).collect(Collectors.toList());
         return FiveMindata.builder().data388Dto(data388Dto).build();
  }
}
