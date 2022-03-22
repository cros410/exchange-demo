package com.example.bcpexchange.context.transaction.application;

import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRateUpdated;
import com.example.bcpexchange.context.transaction.domain.Transaction;
import com.example.bcpexchange.context.transaction.domain.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateTransactionsOnExchangeRateUpdated {
    private final TransactionRepository repository;

    @EventListener
    public void on(ExchangeRateUpdated event) {

        List<Transaction> transactionList = this.repository.findAllByExchangeRateId(event.getId());
        transactionList.stream().map(t -> {
            t.setAmounts(new ExchangeRate(event.getId(), event.getAsk(), event.getBid(), null, null, null, null));
            return this.repository.save(t);
        }).collect(Collectors.toList());
    }
}
