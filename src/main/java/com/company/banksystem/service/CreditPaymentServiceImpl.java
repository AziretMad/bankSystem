package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.exceptions.NotFoundCredit;
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
    public CreditPayment create(CreditPaymentModel entity) throws Exception{
        Credit credit = creditService.getById(entity.getId());
        if(credit!=null){
        CreditPayment creditPayment = CreditPayment.builder().amount(entity.getAmount())
                .status(TransactionStatus.AWAITING_PROCESS)
                .credit(credit).build();
        return creditPaymentRepo.save(creditPayment);}
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

    public static BigDecimal paymentCalculator(Credit credit){
        BigDecimal percent = BigDecimal.valueOf(credit.getInterestRate());
        BigDecimal amount = credit.getAmount();
        BigDecimal duration = BigDecimal.valueOf(credit.getDuration());
        BigDecimal monthPercent = percent.divide(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(12));
        //Ежемесячный платеж = Сумма кредита × Ставка/ 1- (1 + Ставка)^ - Срок кредита
        BigDecimal action1 = monthPercent.add(BigDecimal.valueOf(1)).pow(- credit.getDuration());
        BigDecimal action2 = BigDecimal.valueOf(1).subtract(action1);
        BigDecimal monthPayment = amount.multiply(monthPercent).divide(action2);
        return null;
    }
}
