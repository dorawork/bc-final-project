package com.bootcamp.bc_yahoo_finance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Stocks")
@Getter
@Setter
@AllArgsConstructor
@Builder

public class StockEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;

  @Column(unique = true)
  private String symbol;

  public StockEntity(String symbol) {
    this.symbol = symbol;
  }
  
}
