package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.actions.Transaction;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.exceptions.NotFoundAccount;
import com.company.banksystem.exceptions.WrongConfirmationCode;
import com.company.banksystem.exceptions.WrongKeyWordException;
import com.company.banksystem.models.Confirmation;
import com.company.banksystem.models.actions.TransactionModel;
import com.company.banksystem.repository.TransactionRepo;
import com.company.banksystem.service.interfaces.TransactionService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private BankAccountServiceImpl bankAccountService;
    private static int count = 0;

    @Override
    public Transaction create(TransactionModel entity) throws Exception {
        BankAccount accountTo = bankAccountService.getById(entity.getAccountTo().getId());
        BankAccount accountFrom = bankAccountService.getById(entity.getAccountFrom().getId());
        if (accountFrom != null && accountTo != null) {
            Transaction transaction = Transaction.builder().currency(entity.getCurrency())
                    .amount(entity.getAmount())
                    .accountTo(accountTo)
                    .accountFrom(accountFrom)
                    .status(TransactionStatus.AWAITING_PROCESS)
                    .code(getRandomCode()).build();
            return transactionRepo.save(transaction);
        } else throw new NotFoundAccount();
    }

    @Override
    public Transaction getById(Long id) {
        Optional<Transaction> transaction = transactionRepo.findById(id);
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

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @SneakyThrows
    public Transaction confirmation(Confirmation confirmation, String codeWord) {
        Transaction transaction = getById(confirmation.getTransactionId());
        if (transaction != null) {
            if (!transaction.getCode().equals(confirmation.getConfirmationCode())) {
                count++;
                if (count > 3 && transaction.getStatus().equals(TransactionStatus.AWAITING_PROCESS))
                    transaction.setStatus(TransactionStatus.BLOCKED);
                throw new WrongConfirmationCode();
            }
            if (!transaction.getAccountFrom().getCodeWord().equals(codeWord) &&
                    transaction.getStatus().equals(TransactionStatus.AWAITING_PROCESS)) {
                transaction.setStatus(TransactionStatus.FAIL);
                throw new WrongKeyWordException();
            }
            if (transaction.getCode().equals(confirmation.getConfirmationCode())) {
                if (transaction.getAccountFrom().getCodeWord().equals(codeWord)) {
                    if (transaction.getStatus().equals(TransactionStatus.AWAITING_PROCESS))
                        transaction.setStatus(TransactionStatus.OK);
                }
            }
        }
        transactionFinallyProcess(transaction);
        return transaction;
    }

    public Transaction transactionFinallyProcess(Transaction transaction) {
        if (transaction.getStatus().equals(TransactionStatus.OK)) {
            BankAccount accountFrom = bankAccountService.getById(transaction.getAccountFrom().getId());
            accountFrom.setAmount(accountFrom.getAmount().subtract(transaction.getAmount()));
            BankAccount accountTo = bankAccountService.getById(transaction.getAccountTo().getId());
            accountTo.setAmount(accountTo.getAmount().add(transaction.getAmount()));
            bankAccountService.update(accountFrom);
            bankAccountService.update(accountTo);
            transaction.setAccountFrom(accountFrom);
            transaction.setAccountTo(accountTo);
        }
        return transaction;
    }


    public static Integer getRandomCode() {
        Random r = new Random();
        Integer result = r.nextInt((9999 - 1000) + 1) + 1000;
        return result;
    }

}
