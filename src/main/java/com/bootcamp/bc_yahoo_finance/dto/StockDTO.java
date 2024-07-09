package com.bootcamp.bc_yahoo_finance.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString

public class StockDTO {
  @JsonProperty(value = "stock-list")
  private List<String> symbol;
}
