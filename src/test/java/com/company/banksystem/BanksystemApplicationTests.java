package com.company.banksystem;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.service.CreditPaymentServiceImpl;
import com.company.banksystem.service.CreditServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Equals;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
class BanksystemApplicationTests {

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
		BigDecimal amount = CreditPaymentServiceImpl.paymentCalculator(credit);
		System.out.println(amount);
		BigDecimal neededAmount = BigDecimal.valueOf(38095);
		boolean result = false;
		if(amount == neededAmount) {
			result = true;
		}
		assertTrue(result);
	}
}
