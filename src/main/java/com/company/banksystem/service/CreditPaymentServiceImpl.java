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
import java.text.DecimalFormat;
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
            //Погашение осуществляется аннуитетными платежами.
            Double percent = credit.getInterestRate()/100/12;
            BigDecimal amount = credit.getAmount();
            Integer duration = credit.getDuration();
            DecimalFormat df = new DecimalFormat("###.###");
            df.format(percent);
            String s = df.format(percent).replace(',', '.');
            Double d = Double.valueOf(s);
            BigDecimal monthPercent = BigDecimal.valueOf(d);
            //Условие: сумма кредита — 1 000 000 рублей, срок — три года (36 месяцев), ставка — 20%.
            //1. Ставка по кредиту в месяц = годовая процентная ставка / 12 месяцев 20%/12 месяцев/100=0,017.
            //                              action2             action3
            //2. Коэффициент аннуитета = (0,017*(1+0,017)^36/((1+0,017)^36—1)=0,037184.
            //
            //3. Ежемесячный аннуитетный платеж = 0,037184*1 000 000 рублей = 37 184 рубля.
            BigDecimal action1 = monthPercent.add(BigDecimal.valueOf(1)).pow(duration);
            BigDecimal action2 = action1.multiply(monthPercent);
            BigDecimal action3 = action1.subtract(BigDecimal.valueOf(1));
            BigDecimal coefficient = action2.divide(action3, 2);
            BigDecimal monthPayment = amount.multiply(coefficient).setScale(0, 0);
            return monthPayment;
    }
}
