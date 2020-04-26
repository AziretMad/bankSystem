package com.company.banksystem.models;

import com.company.banksystem.enums.Currency;

import java.math.BigDecimal;

public class CreditCalculatorModelRequest {
    BigDecimal amount;
    Currency currency;
    Integer duration;

}
