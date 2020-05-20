package com.company.banksystem.controller;

import com.company.banksystem.entity.actions.DepositAccrual;
import com.company.banksystem.models.actions.DepositAccrualModel;
import com.company.banksystem.service.interfaces.DepositAccrualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositAccrual")
public class DepositAccrualController {
    @Autowired
    DepositAccrualService depositAccrualService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<DepositAccrual> all = depositAccrualService.getAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            DepositAccrual depositAccrual = depositAccrualService.getById(id);
            return new ResponseEntity<>(depositAccrual, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DepositAccrualModel accrualModel)  {
        try {
            DepositAccrual depositAccrual = depositAccrualService.create(accrualModel);
            return new ResponseEntity<>(depositAccrual, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            depositAccrualService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@PathVariable DepositAccrual deposit) {
        try {
            DepositAccrual deposit1 = depositAccrualService.update(deposit);
            return new ResponseEntity<>(deposit1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calculate/{id}")
    public ResponseEntity calculateAccrual(@PathVariable("id") Long id)  {
        try {
            DepositAccrual deposit1 = depositAccrualService.setAccrualByDepositType(id);
            return new ResponseEntity<>(deposit1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
