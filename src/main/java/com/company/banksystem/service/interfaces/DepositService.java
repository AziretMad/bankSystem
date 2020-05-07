package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.models.DepositModel;

public interface DepositService extends BaseService <Deposit, DepositModel>{
    Deposit setInterestRate(Deposit deposit) throws Exception;
}
