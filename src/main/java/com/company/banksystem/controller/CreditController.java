package com.company.banksystem.controller;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.service.CreditServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @SneakyThrows
    @PostMapping
    public ResponseEntity create(@RequestBody CreditModel creditModel) throws Exception {
        Credit credit=creditService.create(creditModel);
        return new ResponseEntity(credit, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")Long id){
        creditService.delete(id);
    }
}
