package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.exceptions.NotFoundCredit;
import com.company.banksystem.exceptions.WrongPaymentAmount;
import com.company.banksystem.models.actions.CreditPaymentModel;
import com.company.banksystem.repository.CreditPaymentRepo;
import com.company.banksystem.service.interfaces.CreditPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class CreditPaymentServiceImpl implements CreditPaymentService {
    @Autowired
    private CreditPaymentRepo creditPaymentRepo;
    @Autowired
    private CreditServiceImpl creditService;
    @Autowired
    private PaymentCalculatorService paymentCalculatorService;

    @Override
    public CreditPayment create(CreditPaymentModel entity) throws Exception{
        Credit credit = creditService.getById(entity.getCreditId());
        if(credit!=null) {
            if (entity.getAmount().equals(paymentCalculatorService.paymentCalculator(credit.getId()))) {
                CreditPayment creditPayment = CreditPayment.builder()
                        .amount(entity.getAmount())
                        .status(TransactionStatus.AWAITING_PROCESS)
                        .credit(credit).build();
                return creditPaymentRepo.save(creditPayment);
            }
            else {
                throw new WrongPaymentAmount();
            }
        }
        else throw new NotFoundCredit();
    }

    @Override
    public CreditPayment getById(Long id) {
        Optional<CreditPayment> creditPayment = creditPaymentRepo.findById(id);
        return creditPayment.get();
    }

    @Override
    public List<CreditPayment> getAll() {
        return creditPaymentRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        creditPaymentRepo.deleteById(id);
    }

    @Override
    public CreditPayment update(CreditPayment entity) {
        return creditPaymentRepo.save(entity);
    }

    public Integer countPaymentsByCredit(Long id){
        return creditPaymentRepo.countAllPaymentsByCredit(id);
    }

    public List<CreditPayment> getPaymentsByCreditId(Long id){
        return creditPaymentRepo.getCreditPaymentsByCreditId(id);
    }

    public List<CreditPayment> getPaymentsByCreditNumber(String number){
        return creditPaymentRepo.getCreditPaymentsByCreditNumber(number);
    }
}
