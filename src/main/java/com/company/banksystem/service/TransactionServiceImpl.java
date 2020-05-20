package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.ExchangeCurrency;
import com.company.banksystem.entity.actions.Transaction;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.exceptions.*;
import com.company.banksystem.models.Confirmation;
import com.company.banksystem.models.actions.TransactionModel;
import com.company.banksystem.repository.TransactionRepo;
import com.company.banksystem.service.interfaces.ExchangeCurrencyService;
import com.company.banksystem.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class
TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private BankAccountServiceImpl bankAccountService;
    private static int count = 0;

    @Autowired
    private ExchangeCurrencyService exchangeCurrencyService;

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


    public Transaction confirmation(Confirmation confirmation, String codeWord) throws Exception {
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
        } else throw new NotFoundTransaction();

        return update(transactionFinallyProcess(transaction));
    }

    private Transaction transactionFinallyProcess(Transaction transaction) throws NotFoundCurrency {
        if (transaction.getStatus().equals(TransactionStatus.OK)) {
            Currency accountToCurrency = transaction.getAccountTo().getCurrency();
            ExchangeCurrency exchangeCurrency;
            Currency accountFromCurrency = transaction.getAccountFrom().getCurrency();
            Currency currency = transaction.getCurrency();
            BankAccount accountFrom = bankAccountService.getById(transaction.getAccountFrom().getId());
            BankAccount accountTo = bankAccountService.getById(transaction.getAccountTo().getId());

            BigDecimal exchangeValue;

            if (accountFromCurrency.equals(accountToCurrency)) {
                accountFrom.setAmount(accountFrom.getAmount().subtract(transaction.getAmount()));
                accountTo.setAmount(accountTo.getAmount().add(transaction.getAmount()));


            } else if (accountFromCurrency.equals(currency) && currency.equals(Currency.KGS)) {
                exchangeCurrency = exchangeCurrencyService.getByCurrency(accountToCurrency);
                exchangeValue = new BigDecimal(exchangeCurrency.getValue());
                accountFrom.setAmount(accountFrom.getAmount().subtract(transaction.getAmount()));
                accountTo.setAmount(accountTo.getAmount().add(transaction.getAmount().divide(exchangeValue, RoundingMode.HALF_UP)));


            } else if (accountToCurrency.equals(Currency.KGS)) {
                exchangeCurrency = exchangeCurrencyService.getByCurrency(accountFromCurrency);
                exchangeValue = new BigDecimal(exchangeCurrency.getValue());
                accountFrom.setAmount(accountFrom.getAmount().subtract(transaction.getAmount()));
                accountTo.setAmount(accountTo.getAmount().add(transaction.getAmount().multiply(exchangeValue)));

            } else {
                exchangeCurrency = exchangeCurrencyService.getByCurrency(accountFromCurrency);
                ExchangeCurrency exchangeCurrencyTo = exchangeCurrencyService.getByCurrency(accountTo.getCurrency());
                exchangeValue = new BigDecimal(exchangeCurrency.getValue());
                BigDecimal exchangeCurrencyToValue = new BigDecimal(exchangeCurrencyTo.getValue());

                BigDecimal convertToSom = transaction.getAmount().multiply(exchangeValue);
                BigDecimal convertToAnotherCurrency = convertToSom.divide(exchangeCurrencyToValue, RoundingMode.HALF_UP);

                accountFrom.setAmount(accountFrom.getAmount().subtract(transaction.getAmount()));
                accountTo.setAmount(accountTo.getAmount().add(convertToAnotherCurrency));
            }


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

 /*   public Transaction exchangeCurrency(Transaction transaction) throws NotFoundCurrency {
        Currency accountTo = transaction.getAccountTo().getCurrency();
        ExchangeCurrency exchangeCurrency;
        Currency accountFrom = transaction.getAccountFrom().getCurrency();
        Currency currency = transaction.getCurrency();
        if (accountFrom.equals(currency) && accountTo.equals(currency)) {
            return transactionFinallyProcess(transaction);
        } else if (accountFrom.equals(currency)&&currency.equals(Currency.KGS)) {
            exchangeCurrency = exchangeCurrencyService.getByCurrency(accountTo);
            transaction.setAmount(transaction.getAmount().multiply(exchangeCurrency.getValue()));
            return transactionFinallyProcess(transaction);
        }
    }*/
}
