package com.bootcamp.bc_yahoo_finance.infira;

import lombok.Getter;

@Getter
public enum Scheme {
  HTTPS("https"),//
  HTTP("http"),//
  ;
  
  private String value;

  private Scheme(String value){
    this.value = value;
  }

  public String lowercase(){
    return this.value.toLowerCase();
  }
}
