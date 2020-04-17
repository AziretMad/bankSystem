package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.repository.CreditRepo;
import com.company.banksystem.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepo creditRepo;
    @Override
    public Credit create(Credit credit) {
        return creditRepo.save(credit);
    }

    @Override
    public Credit getById(Long id) {
        Optional<Credit>credit=creditRepo.findById(id);
        return credit.get();
    }

    @Override
    public List<Credit> getAll() {
        return creditRepo.findAll();
    }

    @Override
    public void delete(Long id) {
    creditRepo.deleteById(id);
    }
}
