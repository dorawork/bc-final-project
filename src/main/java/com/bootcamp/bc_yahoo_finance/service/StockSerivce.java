package com.bootcamp.bc_yahoo_finance.service;

import com.bootcamp.bc_yahoo_finance.entity.StockEnity;
import com.bootcamp.bc_yahoo_finance.model.Stock;

public interface StockSerivce {

Stock getStock();

StockEnity save (StockEnity stock);
}
