package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.actions.Transaction;
import com.company.banksystem.entity.enums.TransactionStatus;
import com.company.banksystem.models.actions.TransactionModel;
import com.company.banksystem.repository.TransactionRepo;
import com.company.banksystem.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private BankAccountServiceImpl bankAccountService;
    @Override
    public Transaction create(TransactionModel entity) {
        BankAccount accountTo=bankAccountService.getById(entity.getAccountTo().getId());
        BankAccount accountFrom=bankAccountService.getById(entity.getAccountFrom().getId());
        Transaction transaction=Transaction.builder().currency(entity.getCurrency())
                .amount(entity.getAmount())
                .accountTo(accountTo)
                .accountFrom(accountFrom)
                .status(TransactionStatus.AWAITING_PROCESS).build();
        return transactionRepo.save(transaction);
    }

    @Override
    public Transaction getById(Long id) {
        Optional<Transaction> transaction=transactionRepo.findById(id);
        return transaction.get();
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepo.findAll();
    }

    @Override
    public void delete(Long id) {
transactionRepo.deleteById(id);
    }
}
