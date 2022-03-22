package com.example.bcpexchange.context.transaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByExchangeRateId(Long exchangeRateId);
}
