package com.company.banksystem.repository;

import com.company.banksystem.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import com.company.banksystem.entity.ExchangeCurrency;
public interface ExchangeCurrencyRepository extends JpaRepository<ExchangeCurrency,Long> {
    ExchangeCurrency findByCurrency(Currency currency);
}
