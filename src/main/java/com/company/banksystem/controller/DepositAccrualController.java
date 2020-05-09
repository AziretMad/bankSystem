package com.company.banksystem.controller;

import com.company.banksystem.entity.actions.DepositAccrual;
import com.company.banksystem.models.actions.DepositAccrualModel;
import com.company.banksystem.service.interfaces.DepositAccrualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositAccrual")
public class DepositAccrualController {
    @Autowired
    DepositAccrualService depositAccrualService;

    @GetMapping
    public List<DepositAccrual> getAll() {
        return depositAccrualService.getAll();
    }

    @GetMapping("/{id}")
    public DepositAccrual getById(@PathVariable("id") Long id) {
        return depositAccrualService.getById(id);
    }

    @PostMapping
    public DepositAccrual create(@RequestBody DepositAccrualModel accrualModel) throws Exception {
        return depositAccrualService.create(accrualModel);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        depositAccrualService.delete(id);
    }

    @PutMapping
    public DepositAccrual update(@PathVariable DepositAccrual deposit) {
        return depositAccrualService.update(deposit);
    }

    @GetMapping("/calculate/{id}")
    public DepositAccrual calculateAccrual(@PathVariable("id")Long id) throws Exception {
        return depositAccrualService.setAccrualByDepositType(id);
    }
}
