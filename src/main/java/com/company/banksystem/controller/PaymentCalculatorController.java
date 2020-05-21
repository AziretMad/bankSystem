package com.company.banksystem.controller;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.actions.PaymentCalculator;
import com.company.banksystem.service.CreditServiceImpl;
import com.company.banksystem.service.PaymentCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/calculator")
public class PaymentCalculatorController {
    @Autowired
    private PaymentCalculatorService paymentCalculatorService;

    @GetMapping("/{id}")
    public ResponseEntity paymentCalculator(@PathVariable("id") Long id){
        try {
            PaymentCalculator paymentCalculator = PaymentCalculator.builder()
                    .amount(paymentCalculatorService.paymentCalculator(id))
                    .build();
            return new ResponseEntity<>(paymentCalculator, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allBy/{id}")
    public ResponseEntity getAllPayments(@PathVariable("id")Long id){
        try {
            List<BigDecimal> list = paymentCalculatorService.getAllPayments(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
