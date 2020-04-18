package com.company.banksystem.controller;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.service.BankAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @GetMapping
    public List<BankAccount> getAll(){
        return bankAccountService.getAll();
    }
    @GetMapping("/{id}")
    public BankAccount getById(@PathVariable("id")Long id){
        return bankAccountService.getById(id);
    }
    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount){
        return bankAccountService.create(bankAccount);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        bankAccountService.delete(id);
    }
}
