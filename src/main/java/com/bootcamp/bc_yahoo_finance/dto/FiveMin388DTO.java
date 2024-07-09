package com.bootcamp.bc_yahoo_finance.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class FiveMin388DTO { //use for postman
  @JsonProperty(value = "5MIN-0388.HK")
  private FiveMindata fiveMin388;

  @Getter
  @Builder
  public static class FiveMindata {
    @JsonProperty(value = "data")
    private List<Data388Dto> data388Dto;

    @Getter
    @Builder
    public static class Data388Dto {
      private String symbol;
      private String marketTime;
      private Long regularMarketUnix;
      private Double regularMarketPrice;
      private Double regularMarketChangePercent;

    }
  }
}
