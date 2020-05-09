package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.actions.DepositAccrual;
import com.company.banksystem.models.actions.DepositAccrualModel;

import java.math.BigDecimal;

public interface DepositAccrualService extends BaseService<DepositAccrual, DepositAccrualModel> {
    DepositAccrual setAccrualByDepositType(Long depositId) throws Exception;
    BigDecimal accrualCalculate(Deposit deposit);
    DepositAccrual findByDepositId(Long id);
}
