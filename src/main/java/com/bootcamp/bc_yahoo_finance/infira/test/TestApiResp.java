package com.bootcamp.bc_yahoo_finance.infira.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.bootcamp.bc_yahoo_finance.infira.ApiResp;
import com.bootcamp.bc_yahoo_finance.infira.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestApiResp {

  public static void main(String[] args) {
    // Java 9
    List<String> strings = List.of("abc", "def");
    // better performance

    // Immutable:
    // strings.set(0, "hello"); // java.lang.UnsupportedOperationException
    // strings.add("hello"); // java.lang.UnsupportedOperationException
    // strings.remove(0); // java.lang.UnsupportedOperationException

    // Java 8
    // input param -> (String... str)
    Arrays.asList("abc", "de");
    List<String> strings2 = Arrays.asList(new String[] {"abc", "Def"});

    strings2.set(0, "hello"); // OK, implies sorting is fine
    // strings2.add("hello"); // java.lang.UnsupportedOperationException
    // strings2.remove(0); // java.lang.UnsupportedOperationException

    new ArrayList<String>();
    new LinkedList<String>();
    // OK -> modify, add, remove

    ApiResp<UserDTO> response = ApiResp.<UserDTO>builder() //
        .ok() // code = 0, message = "Success."
        .data(List.of(new UserDTO())) //
        .build();

    // Test RestTemplate.getForObject()
    // 1. call Web API, return JSON
    // 2. Using ObjectMapper for deserialization

    // Object -> JSON (Serialization)
    // String json = "{ \"name\" : \"Vincent\"}";
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "";
    try {
      json = objectMapper.writeValueAsString(response);
    } catch (JsonProcessingException e) {

    }
    System.out.println(json); //
    // {"code":0,"message":"Success.","data":[{"id":0,"name":null,"address":null,"company":null}]}

    try {
      TypeReference<ApiResp<UserDTO>> reference = new TypeReference<>() {};
      ApiResp<UserDTO> response2 = objectMapper.readValue(json, reference);
      System.out.println(response2); // ApiResp(code=0, message=Success.,
                                     // data=[UserDTO(id=0, name=null,
                                     // address=null, company=null)])
    } catch (JsonProcessingException e) {
      System.out.println("error");
    }
  }
}