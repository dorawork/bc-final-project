package com.bootcamp.bc_yahoo_finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.bootcamp.bc_yahoo_finance.Repository.StockRepository;
import com.bootcamp.bc_yahoo_finance.entity.StockEnity;


public class Apprun implements CommandLineRunner {

  @Autowired
  private StockRepository stockRpository;

  @Override
  public void run(String... args){
    stockRpository.save(new StockEnity());
  }
}
