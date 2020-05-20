package com.company.banksystem.controller;

import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.models.actions.CreditPaymentModel;
import com.company.banksystem.service.CreditPaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditPayment")
public class CreditPaymentController {
    @Autowired
    private CreditPaymentServiceImpl creditPaymentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        try {
            List<CreditPayment> list = creditPaymentService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            CreditPayment creditPayment = creditPaymentService.getById(id);
            return new ResponseEntity<>(creditPayment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody CreditPaymentModel creditPaymentModel) throws Exception {
        try {
            CreditPayment creditPayment = creditPaymentService.create(creditPaymentModel);
            return new ResponseEntity<>(creditPayment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            creditPaymentService.getById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody CreditPayment credit){
        try {
            CreditPayment updated=creditPaymentService.update(credit);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
