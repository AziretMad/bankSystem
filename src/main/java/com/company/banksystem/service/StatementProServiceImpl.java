package com.company.banksystem.service;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.StatementProcessing;
import com.company.banksystem.enums.StatementType;
import com.company.banksystem.enums.Status;
import com.company.banksystem.exceptions.NotFoundCredit;
import com.company.banksystem.exceptions.NotFoundDeposit;
import com.company.banksystem.exceptions.NotFoundStatement;
import com.company.banksystem.repository.StatementProcessRepo;
import com.company.banksystem.service.interfaces.CreditService;
import com.company.banksystem.service.interfaces.DepositService;
import com.company.banksystem.service.interfaces.StatementProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatementProServiceImpl implements StatementProService {
    @Autowired
    private StatementProcessRepo processRepo;
    @Autowired
    private DepositService depositService;
    @Autowired
    private CreditService creditService;

    @Override
    public List<StatementProcessing> getAllByStatementType(StatementType statementType) {
        return processRepo.findAllByStatementType(statementType);
    }

    @Override
    public StatementProcessing create(StatementProcessing statement) throws Exception {
        StatementType type = statement.getStatementType();
        if (type.equals(StatementType.CREDIT)) {
            Credit credit = creditService.getById(statement.getStatementId());
            if (credit != null) {
                credit.setStatus(statement.getStatus());
            } else throw new NotFoundCredit();

        } else if (type.equals(StatementType.DEPOSIT)) {
            Deposit deposit = depositService.getById(statement.getStatementId());
            if (deposit != null) {
                deposit.setStatus(statement.getStatus());
            } else throw new NotFoundDeposit();
        }
        return processRepo.save(statement);
    }

    @Override
    public List<StatementProcessing> getAll() {
        return processRepo.findAll();
    }

    @Override
    public List<StatementProcessing> getAllByIsAccepted(Status status) {
        return processRepo.findAllByStatus(status);
    }

    @Override
    public void deleteById(Long id) throws NotFoundStatement {
        StatementProcessing statement = getById(id);
        if (statement != null)
            processRepo.delete(statement);
        else throw new NotFoundStatement();
    }

    @Override
    public StatementProcessing getById(Long id) {
        return null;
    }
}
