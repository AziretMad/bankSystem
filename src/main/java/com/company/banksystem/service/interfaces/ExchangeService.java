package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.actions.Exchange;
import com.company.banksystem.exceptions.NotFoundCurrency;
import com.company.banksystem.models.actions.ExchangeModel;

import java.math.BigDecimal;

public interface ExchangeService extends BaseService<Exchange, ExchangeModel> {
    BigDecimal exchange(ExchangeModel exchangeModel) throws NotFoundCurrency;
}
