package com.example.bcpexchange.context.exchangeRate.domain;

import com.example.bcpexchange.context.shared.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeRateUpdated extends DomainEvent {
    private Long id;
    private double ask;
    private double bid;

    @Override
    public String eventName() {
        return "EXCHANGE_RATE_UPDATED";
    }
}
