package com.bootcamp.bc_yahoo_finance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.bc_yahoo_finance.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long>{
  
}
