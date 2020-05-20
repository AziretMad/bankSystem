package com.company.banksystem.controller;

import com.company.banksystem.entity.InterestRateDeposit;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.models.InterestRateModel;
import com.company.banksystem.service.interfaces.InterestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interestRate")
public class InterestRateDepController {
    @Autowired
    private InterestRateService rateService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<InterestRateDeposit> all = rateService.getAll();
            return new ResponseEntity<>(all, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody InterestRateModel statementProcessing) {
        try {
            InterestRateDeposit statement = rateService.create(statementProcessing);
            return new ResponseEntity<>(statement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id)  {
       try{ rateService.delete(id);
        return new ResponseEntity(HttpStatus.OK);}
       catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            InterestRateDeposit interestRateDeposit = rateService.getById(id);
            return new ResponseEntity<>(interestRateDeposit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByCurrency/{currency}")
    public ResponseEntity getByCurrency(@PathVariable("currency") Currency currency) {
        try {
            List<InterestRateDeposit> findByCurrency = rateService.findAllByCurrency(currency);
            return new ResponseEntity<>(findByCurrency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByDuration/{duration}")
    public ResponseEntity getByDuration(@PathVariable("duration") Integer duration) {
        try {
            List<InterestRateDeposit> findByDuration = rateService.findAllByDuration(duration);
            return new ResponseEntity<>(findByDuration, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("findByCurrency&Duration{currency}&{duration}")
    public ResponseEntity findInterestRate(@PathVariable("currency") Currency currency, @PathVariable("duration") Integer duration) {
        try {
            InterestRateDeposit findInterestRate = rateService.findByCurrencyAndDuration(currency, duration);
            return new ResponseEntity<>(findInterestRate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody InterestRateDeposit rateDeposit) {
        try {
            InterestRateDeposit interestRateDeposit = rateService.update(rateDeposit);
            return new ResponseEntity<>(interestRateDeposit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
