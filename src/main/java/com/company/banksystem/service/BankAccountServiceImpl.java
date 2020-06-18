package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.Client;
import com.company.banksystem.enums.Status;
import com.company.banksystem.exceptions.NotFoundClient;
import com.company.banksystem.models.BankAccountModel;
import com.company.banksystem.repository.BankAccountRepo;
import com.company.banksystem.service.interfaces.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepo bankAccountRepo;
    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public BankAccount create(BankAccountModel bankAccountModel) throws Exception {
        Client client = clientService.getById(bankAccountModel.getClient().getId());
        if (client != null) {
            BankAccount bankAccount = BankAccount.builder().accountNumber(generateBankAccountNumber())
                    .amount(bankAccountModel.getAmount())
                    .currency(bankAccountModel.getCurrency())
                    .status(Status.INACTIVE)
                    .client(client)
                    .codeWord(bankAccountModel.getCodeWord())
                    .build();
            return bankAccountRepo.save(bankAccount);
        } else throw new NotFoundClient();
    }

    @Override
    public BankAccount getById(Long id) {
        Optional<BankAccount> bankAccount = bankAccountRepo.findById(id);
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

    @Override
    public BankAccount update(BankAccount entity) {
        return bankAccountRepo.save(entity);
    }

    @Override
    public String generateBankAccountNumber() {
        Random r = new Random();
        String depositNumber = "961452";//9-платежная система 61452 - идентификатор банка
        int accountCode = r.nextInt((999999999 - 100000000) + 1) + 100000000;//идентификатор аккаунта
        String number = depositNumber + String.valueOf(accountCode);
        Integer luhn = luhnAlgorithm(number);
        return number + luhn;
    }

    static Integer luhnAlgorithm(String code) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            int n = Integer.parseInt(code.substring(i, i + 1));
            if (i % 2 == 0) {
                n *= 2;
                if (n > 9)
                    n -= 9;
            }
            sum += n;
        }
        if (sum % 10 == 0)
            return 0;
        return 10 - (sum % 10);
    }

    public List<BankAccount> getAllAccountsByClientId(Long id){
        return bankAccountRepo.findBankAccountsByClientId(id);
    }
@Override
    public BankAccount
findBankAccountByAccountNumber(String number){
        return bankAccountRepo.findBankAccountByAccountNumber(number);
    }
}
