package com.bootcamp.bc_yahoo_finance.dto;

import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.entity.SysData388Entity;

@Component
public class SysData388DTOMapper {
  public SysData388DTO map(SysData388Entity s1){
    return SysData388DTO.builder()
    .sysDate(s1.getSysDate())
    .build();
  }
}