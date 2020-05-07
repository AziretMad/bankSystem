package com.company.banksystem.controller;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.models.DepositModel;
import com.company.banksystem.service.interfaces.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private DepositService depositService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<Deposit> depositList = depositService.getAll();
            return new ResponseEntity<>(depositList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            Deposit deposit = depositService.getById(id);
            return new ResponseEntity<>(deposit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Deposit create(@RequestBody DepositModel deposit) throws Exception {
        return depositService.create(deposit);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        depositService.delete(id);
    }

    @PutMapping
    public Deposit update(@PathVariable Deposit deposit) {
        return depositService.update(deposit);
    }
}
