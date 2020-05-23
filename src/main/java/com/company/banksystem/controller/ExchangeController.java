package com.company.banksystem.controller;

import com.company.banksystem.entity.actions.Exchange;
import com.company.banksystem.models.actions.ExchangeModel;
import com.company.banksystem.service.interfaces.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        try {
            List<Exchange> all = exchangeService.getAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody ExchangeModel model) {
        try {
            Exchange exchangeCurrency = exchangeService.create(model);
            return new ResponseEntity<>(exchangeCurrency, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Exchange exchangeCurrency) {
        try {
            Exchange updated = exchangeService.update(exchangeCurrency);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        try {
            exchangeService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/calculateExchangeSum")//доступ имеют все
    public ResponseEntity exchange(@RequestBody ExchangeModel exchangeModel) {
        try {
            BigDecimal amount = exchangeService.exchange(exchangeModel);
            return new ResponseEntity<>(amount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
