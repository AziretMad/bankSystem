package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.enums.CreditPaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
@Service
public class PaymentCalculatorService {
    @Autowired
    private CreditPaymentServiceImpl creditPaymentService;
    @Autowired
    private CreditServiceImpl creditService;

    public BigDecimal annuityPayment(Credit credit){
        //Погашение осуществляется аннуитетными платежами.
        Double percent = credit.getInterestRate()/100/12;
        BigDecimal amount = credit.getAmount();
        Integer duration = credit.getDuration();
        DecimalFormat df = new DecimalFormat("###.###");
        df.format(percent);
        String s = df.format(percent).replace(',', '.');
        Double monthPercent = Double.valueOf(s);
        //Условие: сумма кредита — 1 000 000 рублей, срок — три года (36 месяцев), ставка — 20%.
        //1. Ставка по кредиту в месяц = годовая процентная ставка / 12 месяцев 20%/12 месяцев/100=0,017.
        //                              action2             action3
        //2. Коэффициент аннуитета = (0,017*(1+0,017)^36/((1+0,017)^36—1)=0,037184.
        //
        //3. Ежемесячный аннуитетный платеж = 0,037184*1 000 000 рублей = 37 184 рубля.
        Double action1 = monthPercent * Math.pow(1 + monthPercent, duration);
        Double action2 = Math.pow(1 + monthPercent, duration) - 1;
        Double coefficient = action1/action2;
        BigDecimal monthPayment = amount.multiply(BigDecimal.valueOf(coefficient)).setScale(2, 0);
        return monthPayment;
    }

    public BigDecimal differentiatedPayment(Credit credit){
        ////Погашение осуществляется дифференцированным платежами.
        Double percent = credit.getInterestRate();
        BigDecimal amount = credit.getAmount();
        Integer period = creditPaymentService.getPaymentsByCredit(credit.getId());
        Integer duration = credit.getDuration();
        DecimalFormat df = new DecimalFormat("###.###");
        df.format(percent);
        String s = df.format(percent).replace(',', '.');
        Double d = Double.valueOf(s);
        BigDecimal monthPercent = BigDecimal.valueOf(d);
        BigDecimal basePayment = amount.divide(BigDecimal.valueOf(duration), 2);
        GregorianCalendar calendar = new GregorianCalendar();
        Integer yearDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        Integer monthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        BigDecimal balance = amount.subtract(basePayment.multiply(BigDecimal.valueOf(period)));
        BigDecimal interestCharges = balance.multiply(BigDecimal.valueOf(percent))
                .multiply(BigDecimal.valueOf(monthDay))
                .divide(BigDecimal.valueOf(yearDays),2);
        BigDecimal monthPayment = basePayment.add(interestCharges);
        return monthPayment;
    }

    public BigDecimal paymentCalculator(Long id){
        Credit credit = creditService.getById(id);
        if(credit.getCreditType().equals(CreditPaymentType.EQUAL)){
            return annuityPayment(credit);
        }
        else if(credit.getCreditType().equals(CreditPaymentType.DIFFERENTIATED)){
            return differentiatedPayment(credit);
        }
        return null;
    }
}
