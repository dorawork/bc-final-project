package com.bootcamp.bc_yahoo_finance.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bootcamp.bc_yahoo_finance.entity.YahooStockEntity;


@Repository
public interface YahooRepository extends JpaRepository<YahooStockEntity,Long> {
  @Query("SELECT e FROM YahooStockEntity e WHERE e.symbol = ?1 AND e.type = ?2 AND YEAR(e.apiDatetime) = YEAR(?3) AND MONTH(e.apiDatetime) = MONTH(?3) AND DAY(e.apiDatetime) = DAY(?3) ORDER BY ABS(TIMESTAMPDIFF(SECOND, e.apiDatetime, CURRENT_TIMESTAMP)) ASC")
List<YahooStockEntity> findNewestEntityBySymbolAndType
(String symbol,String type,LocalDateTime localDateTime);

@Query("SELECT e FROM YahooStockEntity e WHERE e.symbol = ?1 ORDER BY e.MarketTime DESC LIMIT 1")
YahooStockEntity getLatestDataBySymbol(String symbol);

@Query("SELECT e FROM YahooStockEntity e ORDER BY e.MarketTime DESC LIMIT 1")
    Optional<YahooStockEntity> findLatestData();





  
}