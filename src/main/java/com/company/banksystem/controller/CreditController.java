package com.company.banksystem.controller;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.service.CreditServiceImpl;
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
    @GetMapping("all")
    public ResponseEntity getAll(){
        try {
            List<Credit> list = creditService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id")Long id){
        try {
            Credit credit = creditService.getById(id);
            return new ResponseEntity<>(credit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("add")
    public ResponseEntity create(@RequestBody CreditModel creditModel)  {
        try {
            Credit credit = creditService.create(creditModel);
            return new ResponseEntity<>(credit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        try {
             creditService.getById(id);
            return new ResponseEntity( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("update")
    public ResponseEntity update(@RequestBody Credit credit){
        try {
            Credit updated=creditService.update(credit);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{number}")
    public ResponseEntity findCreditByNumber(@PathVariable("number")String number){
        try{
            Credit credit = creditService.findCreditByNumber(number);
            return new ResponseEntity(credit, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity findAllCreditsByClientId(@PathVariable("id") Long id){
        try{
            List<Credit> list = creditService.findAllCreditByClientId(id);
            return new ResponseEntity(list, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
