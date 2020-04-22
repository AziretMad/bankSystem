package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.models.ClientModel;
import com.company.banksystem.models.DepositModel;
import com.company.banksystem.repository.DepositRepo;
import com.company.banksystem.service.interfaces.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepositServiceImpl implements DepositService  {
@Autowired
private DepositRepo depositRepo;
    @Override
    public Deposit create(DepositModel depositModel) {
        ClientModel clientModel = depositModel.getClientModel();
        Client client = Client.builder()
                .fullName(clientModel.getFullName())
                .address(clientModel.getAddress())
                .telephone(clientModel.getTelephone())
                .build();
        Deposit deposit = Deposit.builder()
                .depositNumber(depositModel.getDepositNumber())
                .amount(depositModel.getAmount())
                .interestRate(depositModel.getInterestRate())
                .dateOfCreation(depositModel.getDateOfCreation())
                .dateOfClosing(depositModel.getDateOfClosing())
                .duration(depositModel.getDuration())
                .status(depositModel.getStatus())
                .depositType(depositModel.getDepositType())
                .client(client)
                .build();
        return depositRepo.save(deposit);
    }

    @Override
    public Deposit getById(Long id) {
        Optional<Deposit>deposit=depositRepo.findById(id);
        return deposit.get();
    }

    @Override
    public List<Deposit> getAll() {
        return depositRepo.findAll();
    }

    @Override
    public void delete(Long id) {
    depositRepo.deleteById(id);
    }
}
