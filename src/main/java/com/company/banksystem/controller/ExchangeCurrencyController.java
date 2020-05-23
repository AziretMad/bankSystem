package com.company.banksystem.controller;

import com.company.banksystem.entity.ExchangeCurrency;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.models.ExchangeCurrencyModel;
import com.company.banksystem.service.interfaces.ExchangeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchangeCurrency")
public class ExchangeCurrencyController {
    @Autowired
    private ExchangeCurrencyService currencyService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        try {
            List<ExchangeCurrency> all = currencyService.getAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody ExchangeCurrencyModel model) {
        try {
            ExchangeCurrency exchangeCurrency = currencyService.create(model);
            return new ResponseEntity<>(exchangeCurrency, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ExchangeCurrency exchangeCurrency) {
        try {
            ExchangeCurrency updated = currencyService.update(exchangeCurrency);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            currencyService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByCurrency/{currency}")       //доступ имеют все
    public ResponseEntity getByCurrency(@PathVariable("currency") Currency currency) {
        try {
            ExchangeCurrency getByCurrency = currencyService.getByCurrency(currency);
            return new ResponseEntity<>(getByCurrency
                    , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            ExchangeCurrency exchangeCurrency = currencyService.getById(id);
            return new ResponseEntity<>(exchangeCurrency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
