package com.company.banksystem;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.actions.PaymentCalculator;
import com.company.banksystem.service.CreditPaymentServiceImpl;
import com.company.banksystem.service.CreditServiceImpl;
import com.company.banksystem.service.PaymentCalculatorService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
class BanksystemApplicationTests {
	@Autowired
	private PaymentCalculatorService paymentCalculatorService;

	@Test
	void contextLoads() {
	}

	public static void assertTrue(String message, boolean condition) {
		if (!condition) {
			fail(message);
		}

	}

	public static void assertTrue(boolean condition) {
		assertTrue((String)null, condition);
	}

	@Test
	void testCalculator() {
		Credit credit = Credit.builder()
				.duration(24)
				.amount(BigDecimal.valueOf(800000))
				.interestRate(12D).build();
		BigDecimal amount = paymentCalculatorService.annuityPayment(credit);
		System.out.println(amount);
		BigDecimal neededAmount = BigDecimal.valueOf(38095);
		boolean result = false;
		if(amount == neededAmount) {
			result = true;
		}
		assertTrue(result);
	}
}
