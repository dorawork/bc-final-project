package com.bootcamp.bc_yahoo_finance.entity.Mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;
import com.bootcamp.bc_yahoo_finance.model.DateType;
import com.bootcamp.bc_yahoo_finance.model.Stock;

@Component
public class YahooStockEnityMapper {

  public YahooStockEntity map5M(Stock s1) {
    Instant instant = Instant.ofEpochSecond(s1.getQuoteResponse().getResult().get(0).getRegularMarketTime());
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

    return YahooStockEntity.builder()
        .regularMarketTime(s1.getQuoteResponse().getResult().get(0).getRegularMarketTime())
        .regularMarketPrice(s1.getQuoteResponse().getResult().get(0).getRegularMarketPrice())
        .regularMarketChangePercent(s1.getQuoteResponse().getResult().get(0).getRegularMarketChangePercent())
        .bid(s1.getQuoteResponse().getResult().get(0).getBid())
        .bidSize(s1.getQuoteResponse().getResult().get(0).getBidSize())
        .ask(s1.getQuoteResponse().getResult().get(0).getAsk())
        .askSize(s1.getQuoteResponse().getResult().get(0).getAskSize())
        .type(DateType.FiveMinutes.getSymbol())
        .apiDatetime(LocalDateTime.now())
        .MarketTime(localDateTime)
        .symbol(s1.getQuoteResponse().getResult().get(0).getSymbol())
        .build();
  }

  public YahooStockEntity mapdaily(Stock s1) {
    Instant instant = Instant.ofEpochSecond(s1.getQuoteResponse().getResult().get(0).getRegularMarketTime());
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

    return YahooStockEntity.builder()
        .regularMarketTime(s1.getQuoteResponse().getResult().get(0).getRegularMarketTime())
        .regularMarketPrice(s1.getQuoteResponse().getResult().get(0).getRegularMarketPrice())
        .regularMarketChangePercent(s1.getQuoteResponse().getResult().get(0).getRegularMarketChangePercent())
        .bid(s1.getQuoteResponse().getResult().get(0).getBid())
        .bidSize(s1.getQuoteResponse().getResult().get(0).getBidSize())
        .ask(s1.getQuoteResponse().getResult().get(0).getAsk())
        .askSize(s1.getQuoteResponse().getResult().get(0).getAskSize())
        .type(DateType.Daily.getSymbol())
        .apiDatetime(LocalDateTime.now())
        .MarketTime(localDateTime)
        .symbol(s1.getQuoteResponse().getResult().get(0).getSymbol())
        .build();
  }
}
