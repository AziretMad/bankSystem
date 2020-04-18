package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.repository.BankAccountRepo;
import com.company.banksystem.service.interfaces.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Override
    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepo.save(bankAccount);
    }

    @Override
    public BankAccount getById(Long id) {
        Optional<BankAccount>bankAccount=bankAccountRepo.findById(id);
        return bankAccount.get();
    }

    @Override
    public List<BankAccount> getAll() {
        return bankAccountRepo.findAll();
    }

    @Override
    public void delete(Long id) {
    bankAccountRepo.deleteById(id);
    }
}
