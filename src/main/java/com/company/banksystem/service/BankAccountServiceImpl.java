package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.Client;
import com.company.banksystem.exceptions.NotFoundClient;
import com.company.banksystem.models.BankAccountModel;
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
    @Autowired
    private ClientServiceImpl clientService;
    @Override
    public BankAccount create(BankAccountModel bankAccountModel) throws Exception{
        Client client = clientService.getById(bankAccountModel.getClientModel().getId());
        if(client!=null){
        BankAccount bankAccount = BankAccount.builder().accountNumber(bankAccountModel.getAccountNumber())
                .amount(bankAccountModel.getAmount())
                .currency(bankAccountModel.getCurrency())
                .dateOfCreation(bankAccountModel.getDateOfCreation())
                .status(bankAccountModel.getStatus())
                .client(client)
                .codeWord(bankAccountModel.getCodeWord())
                .build();
        return bankAccountRepo.save(bankAccount);}
        else throw new NotFoundClient();
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

    @Override
    public BankAccount update(BankAccount entity) {
        return bankAccountRepo.save(entity);
    }

}
