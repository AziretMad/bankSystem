package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.models.DepositModel;

public interface DepositService extends BaseService<Deposit, DepositModel> {
    Double setInterestRate(DepositModel deposit) throws Exception;
    String generateDepositNumber();
    Integer luhnAlgorithms(String code);
}
