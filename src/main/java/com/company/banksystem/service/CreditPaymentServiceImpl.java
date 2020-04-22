package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.repository.CreditPaymentRepo;
import com.company.banksystem.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CreditPaymentServiceImpl implements CreditService {
    @Autowired
    private CreditPaymentRepo creditPaymentRepo;
    @Override
    public CreditPayment create(CreditPayment entity) {
        return null;
    }

    @Override
    public CreditPayment getById(Long id) {
        return null;
    }

    @Override
    public List<CreditPayment> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
