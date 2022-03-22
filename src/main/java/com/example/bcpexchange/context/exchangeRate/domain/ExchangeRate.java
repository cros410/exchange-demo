package com.example.bcpexchange.context.exchangeRate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private double ask;
    private double bid;
    @CreationTimestamp
    private Date created;
    @CreationTimestamp
    private Date updated;
    private String createdBy;
    private String updatedBy;
}
