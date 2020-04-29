package com.company.banksystem.controller;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.models.actions.CreditPaymentModel;
import com.company.banksystem.service.CreditPaymentServiceImpl;
import com.company.banksystem.service.CreditServiceImpl;
import com.company.banksystem.service.interfaces.CreditPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditPayment")
public class CreditPaymentController {
    @Autowired
    private CreditPaymentServiceImpl creditPaymentService;

    @Autowired
    private CreditServiceImpl creditService;

    @GetMapping
    public List<CreditPayment> getAll() {
        return creditPaymentService.getAll();
    }

    @GetMapping("/{id}")
    public CreditPayment getById(@PathVariable("id") Long id) {
        return creditPaymentService.getById(id);
    }

    @PostMapping
    public CreditPayment create(@RequestBody CreditPaymentModel creditPaymentModel) throws Exception{
        return creditPaymentService.create(creditPaymentModel);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        creditPaymentService.delete(id);
    }
}
