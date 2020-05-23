package com.company.banksystem.service;

import com.company.banksystem.entity.ExchangeCurrency;
import com.company.banksystem.entity.actions.Exchange;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.exceptions.NotFoundCurrency;
import com.company.banksystem.models.actions.ExchangeModel;
import com.company.banksystem.repository.ExchangeRepo;
import com.company.banksystem.service.interfaces.ExchangeCurrencyService;
import com.company.banksystem.service.interfaces.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    private ExchangeRepo exchangeRepo;

    @Autowired
    private ExchangeCurrencyService currencyService;

    @Override
    public Exchange create(ExchangeModel entity) throws NotFoundCurrency {
        Exchange exchange = Exchange.builder().currencyFrom(entity.getCurrencyFrom())
                .currencyTo(entity.getCurrencyTo())
                .amount(entity.getAmount())
                .exchangeAmount(exchange(entity)).build();
        return exchangeRepo.save(exchange);
    }

    @Override
    public Exchange getById(Long id) {
        Optional<Exchange> currency = exchangeRepo.findById(id);
        return currency.get();
    }

    @Override
    public List<Exchange> getAll() {
        return exchangeRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        Exchange exchange = getById(id);
        exchangeRepo.delete(exchange);
    }

    @Override
    public Exchange update(Exchange entity) {
        return exchangeRepo.save(entity);
    }

    @Override
    public BigDecimal exchange(ExchangeModel exchangeModel) throws NotFoundCurrency {
        ExchangeCurrency buyingAmount = currencyService.getByCurrency(exchangeModel.getCurrencyFrom());
        ExchangeCurrency sellingAmount = currencyService.getByCurrency(exchangeModel.getCurrencyTo());
        BigDecimal sum = null;
        if (!buyingAmount.getCurrency().equals(Currency.KGS) && !sellingAmount.getCurrency().equals(Currency.KGS))
            sum = exchangeModel.getAmount().multiply(new BigDecimal(buyingAmount.getBuying()))
                    .divide(new BigDecimal(sellingAmount.getSelling()), 2, RoundingMode.HALF_UP);
        else if (buyingAmount.getCurrency().equals(Currency.KGS))
            sum = exchangeModel.getAmount().divide(new BigDecimal(sellingAmount.getSelling()), 2, RoundingMode.HALF_UP);
        else if (sellingAmount.getCurrency().equals(Currency.KGS))
            sum = exchangeModel.getAmount().multiply(new BigDecimal(buyingAmount.getBuying()), MathContext.DECIMAL32);
        return sum;//TODO округлить до ,00
    }
}
