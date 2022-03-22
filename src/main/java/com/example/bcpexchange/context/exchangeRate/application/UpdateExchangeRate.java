package com.example.bcpexchange.context.exchangeRate.application;

import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRateRepository;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRateUpdated;
import com.example.bcpexchange.context.shared.domain.EventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateExchangeRate {
    private final ExchangeRateRepository repository;
    private final EventBus eventBus;

    public ExchangeRate run(ExchangeRate exchangeRate, String username) {
        exchangeRate.setUpdated(new Date());
        exchangeRate.setUpdatedBy(username);
        this.eventBus.publish(new ExchangeRateUpdated(exchangeRate.getId(), exchangeRate.getAsk(), exchangeRate.getBid()));
        return repository.save(exchangeRate);
    }
}
