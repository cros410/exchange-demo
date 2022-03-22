package com.example.bcpexchange.context.exchangeRate.application;

import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ListExchangeRate {
    private final ExchangeRateRepository repository;

    public List<ExchangeRate> run() {
        return repository.findAll();
    }
}
