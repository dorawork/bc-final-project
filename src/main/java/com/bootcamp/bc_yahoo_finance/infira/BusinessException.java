package com.bootcamp.bc_yahoo_finance.infira;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {

  private int code;

  public BusinessException(SysCode sysCode) {
    super(sysCode.getDesc()); //enum Desc
    this.code = sysCode.getCode();
  }

}