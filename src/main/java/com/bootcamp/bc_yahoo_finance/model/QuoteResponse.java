package com.bootcamp.bc_yahoo_finance.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponse {
  private List<Result> result;
  private Error error;
  
}