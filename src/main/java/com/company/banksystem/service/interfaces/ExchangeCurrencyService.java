package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.ExchangeCurrency;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.exceptions.NotFoundCurrency;
import com.company.banksystem.models.ExchangeCurrencyModel;

public interface ExchangeCurrencyService extends BaseService<ExchangeCurrency, ExchangeCurrencyModel> {
    ExchangeCurrency getByCurrency(Currency currency) throws NotFoundCurrency;
}
