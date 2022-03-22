package com.example.bcpexchange.context.transaction.domain;

import com.example.bcpexchange.context.exchangeRate.domain.ExchangeRate;
import com.example.bcpexchange.context.shared.Utils;
import com.example.bcpexchange.context.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency chargeCurrency;
    private double chargeAmount;
    @Enumerated(EnumType.STRING)
    private Currency depositCurrency;
    private double depositAmount;
    private double exchangeRate;
    private Long exchangeRateId;
    @CreationTimestamp
    private Date created;
    @ManyToOne()
    private User user;

    public void setCurrencies() {
        if (chargeCurrency == Currency.USD) depositCurrency = Currency.PEN;
        if (chargeCurrency == Currency.PEN) depositCurrency = Currency.USD;
    }

    public void setAmounts(ExchangeRate exchangeRate) {
        if (chargeCurrency == Currency.USD) {
            depositAmount = chargeAmount * exchangeRate.getBid();
            this.exchangeRate = exchangeRate.getBid();
            this.exchangeRateId = exchangeRate.getId();
        }
        if (chargeCurrency == Currency.PEN) {
            depositAmount = chargeAmount / exchangeRate.getAsk();
            this.exchangeRate = exchangeRate.getAsk();
            this.exchangeRateId = exchangeRate.getId();
        }
        depositAmount = Utils.round(depositAmount, 3);
        chargeAmount = Utils.round(chargeAmount, 3);
    }

}
