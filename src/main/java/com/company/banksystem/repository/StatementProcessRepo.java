package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.StatementProcessing;
import com.company.banksystem.enums.StatementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatementProcessRepo extends JpaRepository<StatementProcessing,Long> {
    List<StatementProcessing> findAllByStatementType(StatementType statementType);
  //  List<StatementProcessing> findAllByStatus(Status status);


}
