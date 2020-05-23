package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.actions.PaymentCalculator;
import com.company.banksystem.enums.CreditPaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PaymentCalculatorService<list> {
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
        Double percent = credit.getInterestRate()/100;
        BigDecimal amount = credit.getAmount();
        Integer period = creditPaymentService.countPaymentsByCredit(credit.getId());
        Integer duration = credit.getDuration();
        DecimalFormat df = new DecimalFormat("###.###");
        df.format(percent);
        String s = df.format(percent).replace(',', '.');
        Double d = Double.valueOf(s);
        BigDecimal yearPercent = BigDecimal.valueOf(d);
        BigDecimal basePayment = amount.divide(BigDecimal.valueOf(duration), 2);
        GregorianCalendar calendar = new GregorianCalendar();
        Integer yearDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        Integer monthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        BigDecimal subtract = basePayment.multiply(BigDecimal.valueOf(period));
        BigDecimal balance = amount.subtract(subtract);
        BigDecimal interestCharges = balance.multiply(yearPercent)
                .multiply(BigDecimal.valueOf(monthDay))
                .divide(BigDecimal.valueOf(yearDays),2);
        BigDecimal monthPayment = basePayment.add(interestCharges).setScale(2, 0);
        return monthPayment;
    }

    public BigDecimal differentiatedForMonth(Credit credit, Integer month){
        ////Погашение осуществляется дифференцированным платежами.
        Double percent = credit.getInterestRate()/100;
        BigDecimal amount = credit.getAmount();
        Integer period = month;
        Integer duration = credit.getDuration();
        DecimalFormat df = new DecimalFormat("###.###");
        df.format(percent);
        String s = df.format(percent).replace(',', '.');
        Double d = Double.valueOf(s);
        BigDecimal yearPercent = BigDecimal.valueOf(d);
        BigDecimal basePayment = amount.divide(BigDecimal.valueOf(duration), 2);
        GregorianCalendar calendar = new GregorianCalendar();
        Integer yearDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        Integer monthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        BigDecimal subtract = basePayment.multiply(BigDecimal.valueOf(period));
        BigDecimal balance = amount.subtract(subtract);
        BigDecimal interestCharges = balance.multiply(yearPercent)
                .multiply(BigDecimal.valueOf(monthDay))
                .divide(BigDecimal.valueOf(yearDays),2);
        BigDecimal monthPayment = basePayment.add(interestCharges).setScale(2, 0);
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

    public BigDecimal paymentCalculator(Credit credit){
        if(credit.getCreditType().equals(CreditPaymentType.EQUAL)){
            return annuityPayment(credit);
        }
        else if(credit.getCreditType().equals(CreditPaymentType.DIFFERENTIATED)){
            return differentiatedPayment(credit);
        }
        return null;
    }

    public List<PaymentCalculator> getAllPayments(Long id){
        Credit credit = creditService.getById(id);
        List<PaymentCalculator> list = new ArrayList<>();
        if(credit.getCreditType().equals(CreditPaymentType.EQUAL)){
            list = getAnnuityList(credit);
        }
        else if(credit.getCreditType().equals(CreditPaymentType.DIFFERENTIATED)){
            list = getDifferentiatedList(credit);
        }
        return list;
    }

    public BigDecimal getNeededAmount(Long id){
        Credit credit = creditService.getById(id);
        BigDecimal monthPayment = annuityPayment(credit);
        return monthPayment.multiply(BigDecimal.valueOf(credit.getDuration()));
    }

    public List<PaymentCalculator> getAnnuityList(Credit credit){
        List<PaymentCalculator> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(credit.getCreatedDate());
        for(int i = 0; i < credit.getDuration(); i++){
            int temp = i;
            calendar.add(Calendar.MONTH, temp++);
            list.add(PaymentCalculator.builder()
                    .id(Long.valueOf(i+1))
                    .amount(annuityPayment(credit))
                    .date(calendar.getTime())
                    .build());
        }
        return list;
    }

    public List<PaymentCalculator> getDifferentiatedList(Credit credit){
        List<PaymentCalculator> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(credit.getCreatedDate());
        for(int i = 0; i < credit.getDuration(); i++){
            int temp = i;
            calendar.add(Calendar.MONTH, temp++);
            list.add(PaymentCalculator.builder()
                    .id(Long.valueOf(i+1))
                    .amount(differentiatedForMonth(credit, i))
                    .date(calendar.getTime())
                    .build());
        }
        return list;
    }
}
