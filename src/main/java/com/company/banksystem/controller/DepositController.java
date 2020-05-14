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
    public ResponseEntity create(@RequestBody DepositModel deposit) {
        try {
            Deposit deposit1 = depositService.create(deposit);
            return new ResponseEntity<>(deposit1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            depositService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@PathVariable Deposit deposit) {
        try {
            Deposit deposit1 = depositService.update(deposit);
            return new ResponseEntity<>(deposit1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
