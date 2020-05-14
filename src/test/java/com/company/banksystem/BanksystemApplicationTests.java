package com.company.banksystem;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.service.CreditPaymentServiceImpl;
import com.company.banksystem.service.DepositServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

	@Test
	void testNumber(){
		String number="564896576621235";
		DepositServiceImpl depositService=new DepositServiceImpl();
		Integer amount= depositService.luhnAlgorithms(number);
		Integer neededAmount=2;
		boolean result = false;
		if(amount.equals(neededAmount) ) {
			result = true;
		}
		assertTrue(result);
	}
}
