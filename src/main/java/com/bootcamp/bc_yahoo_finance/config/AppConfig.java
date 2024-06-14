package com.bootcamp.bc_yahoo_finance.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.bc_yahoo_finance.model.Stock;

@Configuration
public class AppConfig {
  
@Bean
RestTemplate retTemplate(){
  return new RestTemplate();
}

@Bean
List<Stock> stocks(){
  return new ArrayList<>();
}
}
