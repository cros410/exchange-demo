package com.example.bcpexchange.context.exchangeRate.application;

import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterExchangeRate {
    private final ExchangeRateRepository repository;

    public ExchangeRate run(ExchangeRate data, String username){
        data.setCreatedBy(username);
        return  repository.save(data);
    }
}
