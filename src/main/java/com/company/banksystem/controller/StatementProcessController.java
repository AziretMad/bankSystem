package com.company.banksystem.controller;

import com.company.banksystem.entity.StatementProcessing;
import com.company.banksystem.enums.StatementType;
import com.company.banksystem.enums.Status;
import com.company.banksystem.exceptions.NotFoundStatement;
import com.company.banksystem.service.interfaces.StatementProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statementProcess")
public class StatementProcessController {

    @Autowired
    private StatementProService proService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<StatementProcessing> all = proService.getAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody StatementProcessing statementProcessing) {
        try {
            StatementProcessing statement = proService.create(statementProcessing);
            return new ResponseEntity<>(statement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) throws NotFoundStatement {
        proService.deleteById(id);
    }

    @GetMapping("/getAllByStatementType/{type}")
    public ResponseEntity getAllByType(@PathVariable("type") StatementType type) {
        try {
            List<StatementProcessing> processing = proService.getAllByStatementType(type);
            return new ResponseEntity<>(processing, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/getByAllStatus/{status}")
    public ResponseEntity getAllByStatus(@PathVariable("status")Status status){
        try{
            List<StatementProcessing>list=proService.getAllByIsAccepted(status);
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
