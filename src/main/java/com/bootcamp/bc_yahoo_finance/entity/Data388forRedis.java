package com.bootcamp.bc_yahoo_finance.entity;

import java.util.List;
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

  public class Data388forRedis {
    private List<DataforCach> dataforCach ;
  }
