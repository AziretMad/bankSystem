package com.company.banksystem.controller;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.service.DepositServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    @Autowired
    private DepositServiceImpl depositService;

    @GetMapping
    public List<Deposit> getAll() {
        return depositService.getAll();
    }

    @GetMapping("/{id}")
    public Deposit getById(@PathVariable("id") Long id) {
        return depositService.getById(id);
    }

    @PostMapping
    public Deposit create(@RequestBody Deposit bankAccount) {
        return depositService.create(bankAccount);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        depositService.delete(id);
    }
}
