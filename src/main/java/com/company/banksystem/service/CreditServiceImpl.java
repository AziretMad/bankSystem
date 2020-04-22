package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.models.ClientModel;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.repository.ClientRepo;
import com.company.banksystem.repository.CreditRepo;
import com.company.banksystem.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private ClientServiceImpl clientService;
    @Override
    public Credit create(CreditModel creditModel) {
        Client client = clientService.getById(creditModel.getClientModel().getId());
        Credit credit = Credit.builder()
                .creditNumber(creditModel.getCreditNumber())
                .amount(creditModel.getAmount())
                .interestRate(creditModel.getInterestRate())
                .dateOfCreation(creditModel.getDateOfCreation())
                .dateOfClosing(creditModel.getDateOfClosing())
                .duration(creditModel.getDuration())
                .client(client)
                .build();
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
