package com.bootcamp.bc_yahoo_finance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.bc_yahoo_finance.dto.FiveMin388DTO;
import com.bootcamp.bc_yahoo_finance.dto.StockDTO;
import com.bootcamp.bc_yahoo_finance.dto.SysData388DTO;
import com.bootcamp.bc_yahoo_finance.dto.YahooStockDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockOperation {

  @GetMapping(value = "/jsonplaceholder/stocks") //datebase
  StockDTO getStock() throws JsonProcessingException;

  @GetMapping(value = "/update/stock") //update database clean 先可以REDIS 最
  void updateStock() throws JsonProcessingException;

  @GetMapping(value = "/stock/symbol/type")
  List<YahooStockDTO> getYahooStockDTOs
  (@RequestParam String symbol, @RequestParam String type) throws JsonProcessingException;

  @GetMapping(value =  "/388/sysdata")
  SysData388DTO getSysDate388() throws JsonProcessingException;
  
  @GetMapping(value = "/stock/388")
  FiveMin388DTO get388Data() throws JsonProcessingException;
}
