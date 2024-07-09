package com.bootcamp.bc_yahoo_finance.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.bc_yahoo_finance.Holiday.Holiday;
import com.bootcamp.bc_yahoo_finance.infira.RedisHelper;
import com.bootcamp.bc_yahoo_finance.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

  @Bean
  RestTemplate retTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "stocks")
  List<Result> stocks() {
    return new ArrayList<>();
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
  @Bean
  RedisHelper redisHelper(RedisConnectionFactory factory,
  ObjectMapper objectMapper){
    return new RedisHelper(factory, objectMapper);
  }

  @Bean
  public Holiday holiday(){
    return new Holiday();
  }
}
