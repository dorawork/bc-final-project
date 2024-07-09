package com.bootcamp.bc_yahoo_finance.mapper;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.dto.StockDTO;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;

@Component
public class StockDTOMapper {


  public StockDTO map(List<StockEntity> stocksEntity){
    List<String> symbol=stocksEntity.stream().map(e->e.getSymbol())
    .collect(Collectors.toList());
    
    return StockDTO.builder()
    .symbol(symbol)
    .build();
  }
  
}