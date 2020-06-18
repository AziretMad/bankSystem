package com.company.banksystem.controller;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.models.BankAccountModel;
import com.company.banksystem.service.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        try {
            List<BankAccount> list = bankAccountService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            BankAccount bankAccount = bankAccountService.getById(id);
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity create(@RequestBody BankAccountModel bankAccount) {
        try {
            BankAccount bankAccount1 = bankAccountService.create(bankAccount);
            return new ResponseEntity<>(bankAccount1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            bankAccountService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody BankAccount bankAccount){
        try {
            BankAccount updated=bankAccountService.update(bankAccount);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity getAllAccountsByClientId(@PathVariable("id") Long id) {
        try {
            List<BankAccount> list = bankAccountService.getAllAccountsByClientId(id);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchByNumber/{number}")
    public ResponseEntity getAccountByNumber(@PathVariable("number") String number) {
        try {
            BankAccount bankAccount = bankAccountService.findBankAccountByAccountNumber(number);
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
