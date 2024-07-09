package com.bootcamp.bc_yahoo_finance.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.bc_yahoo_finance.service.KeyService;

@Service
public class KeyServiceImpl implements KeyService{

  @Override
  public String getCrumb(){

    String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";

    String crumbkey = new RestTemplate().getForObject(url, String.class);
    return crumbkey;
  }

  @Override
  public String hardCodeCrumb(){
    return "R4sCPlD.XsD";
  }
}
