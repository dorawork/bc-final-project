package com.bootcamp.bc_yahoo_finance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.bc_yahoo_finance.entity.HKEXEntity;

@Repository
public interface HKEXRepository extends JpaRepository<HKEXEntity, Long>{
  
}
