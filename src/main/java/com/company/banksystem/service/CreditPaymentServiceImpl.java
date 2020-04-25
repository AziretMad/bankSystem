package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.entity.enums.TransactionStatus;
import com.company.banksystem.models.actions.CreditPaymentModel;
import com.company.banksystem.repository.CreditPaymentRepo;
import com.company.banksystem.service.interfaces.CreditPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class CreditPaymentServiceImpl implements CreditPaymentService {
    @Autowired
    private CreditPaymentRepo creditPaymentRepo;
    @Autowired
    private CreditServiceImpl creditService;

    @Override
    public CreditPayment create(CreditPaymentModel entity) {
        Credit credit = creditService.getById(entity.getId());
        CreditPayment creditPayment = CreditPayment.builder()
                .amount(entity.getAmount())
                .paymentType(entity.getPaymentType())
                .status(TransactionStatus.AWAITING_PROCESS)
                .credit(credit).build();
        return creditPaymentRepo.save(creditPayment);
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

    public void creditPaymentCalculator(Long creditId){
        Credit credit = creditService.getById(creditId);
        Double interestRate = credit.getInterestRate();
        BigDecimal amount = credit.getAmount();
        Integer duration = credit.getDuration();
    }
}
