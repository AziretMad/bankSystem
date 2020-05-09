package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.actions.StatementProcessing;
import com.company.banksystem.enums.StatementType;
import com.company.banksystem.enums.Status;
import com.company.banksystem.exceptions.NotFoundStatement;

import java.util.List;

public interface StatementProService {
    List<StatementProcessing> getAllByStatementType(StatementType statementType);
    StatementProcessing create(StatementProcessing statement) throws Exception;
    List<StatementProcessing>getAll();
    void deleteById(Long id) throws NotFoundStatement;
    StatementProcessing getById(Long id);
    List<StatementProcessing>getAllByIsAccepted(Status status);
}
