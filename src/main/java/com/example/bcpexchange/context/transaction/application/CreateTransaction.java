package com.example.bcpexchange.context.transaction.application;

import com.example.bcpexchange.context.exchangeRate.application.FindCurrentExchangeRate;
import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import com.example.bcpexchange.context.transaction.domain.Transaction;
import com.example.bcpexchange.context.transaction.domain.TransactionRepository;
import com.example.bcpexchange.context.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateTransaction {

    private final TransactionRepository repository;
    private final FindCurrentExchangeRate findCurrentExchangeRate;
    private final UserService userService;

    public Transaction run(Transaction data, String username) {
        ExchangeRate exchangeRate = this.findCurrentExchangeRate.run();
        data.setCurrencies();
        data.setAmounts(exchangeRate);
        data.setUser(this.userService.findByUsername(username));
        return this.repository.save(data);
    }
}
