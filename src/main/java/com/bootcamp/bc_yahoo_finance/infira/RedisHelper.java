package com.bootcamp.bc_yahoo_finance.infira;

import java.time.Duration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisHelper {
  // Encapsulation RestTemp.class
  // 1. Genrics <String, String>
  // 2. OpsForValus()
  // 3. remove initalalize the object states (serializer)
  // 開箱即用 未寫完

  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper objectMapper;

  public RedisHelper(RedisConnectionFactory factory, ObjectMapper objectMapper) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(RedisSerializer.string());
    redisTemplate.setValueSerializer(RedisSerializer.json());
    redisTemplate.afterPropertiesSet();
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  // Serialization
  public <T> void set(String key, T obj) throws JsonProcessingException {
    String json = this.objectMapper.writeValueAsString(obj);
    this.redisTemplate.opsForValue().set(key, json);
  }

  public <T> void set(String key, T obj, Duration duration)
      throws JsonProcessingException {
    String json = this.objectMapper.writeValueAsString(obj);
    this.redisTemplate.opsForValue().set(key, json, duration);
  }

  // restTemplate.getForObject(url, User.class);
  // redisHelper.get("jph-user", User[].class);
  // Deserialization
  public <T> T get(String key, Class<T> clazz) throws JsonProcessingException {
    String json = this.redisTemplate.opsForValue().get(key);
    return json == null ? null : this.objectMapper.readValue(json, clazz);
  }

}
/*   public XXX get(String key){
    get(key)
  // get the string for redis
  //convert the string to object and return
  }

  public xxx set(String key, Object obj) {
    // put string into the reids
  } */
