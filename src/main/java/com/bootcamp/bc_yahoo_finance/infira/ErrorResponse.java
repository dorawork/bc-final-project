package com.bootcamp.bc_yahoo_finance.infira;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ErrorResponse {
  private int code;
  private String message;

  public static ErrorResponse of(int code, String message) {
    return new ErrorResponse(code, message);
  }
}