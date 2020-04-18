package com.company.banksystem.controller;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.service.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    private CreditServiceImpl creditService;
    @GetMapping
    public List<Credit> getAll(){
        return creditService.getAll();
    }
    @GetMapping("/{id}")
    public Credit getById(@PathVariable("id")Long id){
        return creditService.getById(id);
    }
    @PostMapping
    public Credit create(@RequestBody Credit bankAccount){
        return creditService.create(bankAccount);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        creditService.delete(id);
    }
}
