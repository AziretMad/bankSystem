package com.company.banksystem;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.repository.CreditPaymentRepo;
import com.company.banksystem.service.CreditPaymentServiceImpl;
import com.company.banksystem.service.CreditServiceImpl;
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
	private CreditPaymentRepo creditPaymentRepo;
	@Autowired
	private CreditServiceImpl creditService;
	@Autowired
	private CreditPaymentServiceImpl creditPaymentService;

	@Test
	void contextLoads() {
	}

	@Test
	void testCalculator() {
		Credit credit = Credit.builder()
				.duration(24)
				.amount(BigDecimal.valueOf(800000))
				.interestRate(12D).build();
		BigDecimal amount = CreditPaymentServiceImpl.annuityPayment(credit);
		System.out.println(amount);
		BigDecimal neededAmount = BigDecimal.valueOf(37659);
		boolean result = false;
		if(amount.equals(neededAmount)) {
			result = true;
		}
		assert result;
	}

	@Test
	void findAllPaymentsByCredit() {
		Integer i = creditPaymentService.getPaymentsByCredit(1L);
		boolean result = false;
		if(i == 0){
			result = true;
		}
		assert result;
	}
}
