package com.bootcamp.bc_yahoo_finance.model;

import lombok.Getter;

@Getter

public enum DateType {
  FiveMinutes("5M"),
  Daily("D"),
  Weekly("W"),
  Monthly("M"),
  ;

  private String symbol;
  
  private DateType(String symbol){

    this.symbol=symbol;
 }
  
}