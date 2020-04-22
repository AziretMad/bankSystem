package com.company.banksystem.service;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.actions.DepositAccrual;
import com.company.banksystem.entity.enums.TransactionStatus;
import com.company.banksystem.models.actions.DepositAccrualModel;
import com.company.banksystem.repository.DepositAccrualRepo;
import com.company.banksystem.service.interfaces.DepositAccrualService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DepositAccrualServiceImpl implements DepositAccrualService {
    @Autowired
    private DepositAccrualRepo depositAccrualRepo;
    @Autowired
    private DepositServiceImpl depositService;

    @Override
    public DepositAccrual create(DepositAccrualModel entity) {
        Deposit deposit = depositService.getById(entity.getDeposit().getId());
        DepositAccrual depositAccrual = DepositAccrual.builder().amount(entity.getAmount())
                .status(TransactionStatus.AWAITING_PROCESS)
                .deposit(deposit).build();
        return depositAccrualRepo.save(depositAccrual);
    }

    @Override
    public DepositAccrual getById(Long id) {
        Optional<DepositAccrual> depositAccrual = depositAccrualRepo.findById(id);
        return depositAccrual.get();
    }

    @Override
    public List<DepositAccrual> getAll() {
        return depositAccrualRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        depositAccrualRepo.deleteById(id);
    }
}