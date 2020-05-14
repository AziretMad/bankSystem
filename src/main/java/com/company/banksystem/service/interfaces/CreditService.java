package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.models.CreditModel;

public interface CreditService extends BaseService <Credit, CreditModel>{
    String generateCreditNumber();
    Integer luhnAlgorithms(String code);
}
