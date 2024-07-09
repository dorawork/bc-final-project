package com.bootcamp.bc_yahoo_finance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class DataforCach {
  private String symbol;
  private String marketTime;
  private Long regularMarketUnix;
  private Double regularMarketPrice;
  private Double regularMarketChangePercent;
  
}