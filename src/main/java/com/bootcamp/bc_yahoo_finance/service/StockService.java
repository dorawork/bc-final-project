package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;

import com.bootcamp.bc_yahoo_finance.entity.Data388forRedis;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc_yahoo_finance.entity.SysData388Entity;
import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface StockService {

List<StockEntity> getStock() throws JsonProcessingException; //get (list) database 

void addDate();

List<YahooStockEntity> get5mindate(String symbol, String type) throws JsonProcessingException;

void update5mindata(); //update database

void updateDailydata();

void cleanAllSysDate();

SysData388Entity getSysDate388(String symbol) throws JsonProcessingException;

Data388forRedis getData388forRedis() throws JsonProcessingException;
}
