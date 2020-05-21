package com.company.banksystem;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.enums.CreditPaymentType;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.DepositType;
import com.company.banksystem.service.DepositAccrualServiceImpl;
import com.company.banksystem.service.DepositServiceImpl;
import com.company.banksystem.service.interfaces.DepositAccrualService;
import org.junit.jupiter.api.Test;

import com.company.banksystem.service.PaymentCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BanksystemApplicationTests {
	@Autowired
	private PaymentCalculatorService paymentCalculator;

/*
    @Test
    void testCalculator() {
        Credit credit = Credit.builder()
                .duration(24)
                .amount(BigDecimal.valueOf(800000))
                .interestRate(12D).build();
        BigDecimal amount = CreditPaymentServiceImpl.paymentCalculator(credit);
        System.out.println(amount);
        BigDecimal neededAmount = BigDecimal.valueOf(38095);
        boolean result = false;
        if (amount == neededAmount) {
            result = true;
        }
        assertTrue(result);
    }*/

    @Test
    void testNumber() {
        String number = "564896576621235";
        DepositServiceImpl depositService = new DepositServiceImpl();
        Integer amount = depositService.luhnAlgorithms(number);
        Integer neededAmount = 2;
        boolean result = false;
        if (amount.equals(neededAmount)) {
            result = true;
        }
        assertTrue(result);
    }

    @Test
    void depositAccrualCalculateTest() {
        Deposit deposit = Deposit.builder().duration(18)
                .depositType(DepositType.CUMULATIVE)
                .amount(new BigDecimal(1000000))
                .currency(Currency.KGS)
                .interestRate(10.5D).build();
        DepositAccrualService depositAccrualService = new DepositAccrualServiceImpl();
        BigDecimal amount = depositAccrualService.accrualCalculate(deposit);
        BigDecimal neededAmount = BigDecimal.valueOf(157500);
        boolean result = false;
        if (amount.equals(neededAmount))
            result = true;
        assertTrue(result);
    }



	@Test
	void testCalculator() {
		Credit credit = Credit.builder()
				.duration(24)
				.amount(BigDecimal.valueOf(800000))
                .creditType(CreditPaymentType.EQUAL)
				.interestRate(12D).build();
		BigDecimal amount = paymentCalculator.paymentCalculator(credit);
		System.out.println(amount);
		BigDecimal neededAmount = BigDecimal.valueOf(38095);
		boolean result = false;
		if(amount == neededAmount) {
			result = true;
		}
		assertTrue(result);
	}
}
