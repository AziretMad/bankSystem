package com.company.banksystem.controller;

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

@RestController
@RequestMapping(name = "/paymentCalculator")
public class PaymentCalculatorController {
    @Autowired
    private CreditServiceImpl creditService;
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
}
