package com.bootcamp.bc_yahoo_finance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SysData388DTO {
  @JsonProperty(value = "SYSDATE-0388.HK")
  private String sysDate;
}