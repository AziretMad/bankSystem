package com.company.banksystem.service;

import com.company.banksystem.entity.ExchangeCurrency;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.exceptions.NotFoundCurrency;
import com.company.banksystem.models.ExchangeCurrencyModel;
import com.company.banksystem.repository.ExchangeCurrencyRepository;
import com.company.banksystem.service.interfaces.ExchangeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExchangeCurrencyServiceImpl implements ExchangeCurrencyService {
    @Autowired
    private ExchangeCurrencyRepository currencyRepository;

    @Override
    public ExchangeCurrency getByCurrency(Currency currency) throws NotFoundCurrency {
        ExchangeCurrency exchangeCurrency = currencyRepository.findByCurrency(currency);
        if (currency != null)
            return exchangeCurrency;
        else throw new NotFoundCurrency();
    }

    @Override
    public ExchangeCurrency create(ExchangeCurrencyModel entity) throws Exception {
        ExchangeCurrency currency = ExchangeCurrency.builder()
                .currency(entity.getCurrency())
                .value(entity.getValue())
                .build();
        return currencyRepository.save(currency);
    }

    @Override
    public ExchangeCurrency getById(Long id) {
        Optional<ExchangeCurrency> currency = currencyRepository.findById(id);
        return currency.get();
    }

    @Override
    public List<ExchangeCurrency> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        ExchangeCurrency currency = getById(id);
        currencyRepository.delete(currency);
    }

    @Override
    public ExchangeCurrency update(ExchangeCurrency entity) {
        return currencyRepository.save(entity);
    }
}
