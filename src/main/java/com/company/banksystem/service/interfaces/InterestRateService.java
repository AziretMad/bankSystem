package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.InterestRateDeposit;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.exceptions.NotFoundInterestRate;
import com.company.banksystem.models.InterestRateModel;

import java.util.List;

public interface InterestRateService extends BaseService<InterestRateDeposit, InterestRateModel> {
    List<InterestRateDeposit> findAllByCurrency(Currency currency) throws NotFoundInterestRate;
    List<InterestRateDeposit>findAllByDuration(Integer duration) throws NotFoundInterestRate;
    Double findByCurrencyAndDuration(Currency currency,Integer duration) throws NotFoundInterestRate;
}
