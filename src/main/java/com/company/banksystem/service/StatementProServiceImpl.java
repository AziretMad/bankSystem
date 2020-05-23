package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.actions.StatementProcessing;
import com.company.banksystem.enums.StatementType;
import com.company.banksystem.enums.Status;
import com.company.banksystem.exceptions.NotFoundCredit;
import com.company.banksystem.exceptions.NotFoundDeposit;
import com.company.banksystem.exceptions.NotFoundStatement;
import com.company.banksystem.repository.StatementProcessRepo;
import com.company.banksystem.service.interfaces.BankAccountService;
import com.company.banksystem.service.interfaces.CreditService;
import com.company.banksystem.service.interfaces.DepositService;
import com.company.banksystem.service.interfaces.StatementProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StatementProServiceImpl implements StatementProService {
    @Autowired
    private StatementProcessRepo processRepo;
    @Autowired
    private DepositService depositService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private BankAccountService bankAccountService;

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
                if (statement.getStatus().equals(Status.CLOSED))
                    credit.setClosedDate(new Date());
            } else throw new NotFoundCredit();

        } else if (type.equals(StatementType.DEPOSIT)) {
            Deposit deposit = depositService.getById(statement.getStatementId());
            if (deposit != null) {
                deposit.setStatus(statement.getStatus());
                if (statement.getStatus().equals(Status.CLOSED))
                    deposit.setClosedDate(new Date());

            } else throw new NotFoundDeposit();
        } else if (type.equals(StatementType.BANK_ACCOUNT)) {
            BankAccount bankAccount = bankAccountService.getById(statement.getStatementId());
            if (bankAccount != null) {
                bankAccount.setStatus(statement.getStatus());
                if (statement.getStatus().equals(Status.CLOSED))
                    bankAccount.setClosedDate(new Date());
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
        Optional<StatementProcessing> processing = processRepo.findById(id);
        return processing.orElse(new StatementProcessing());
    }
}
