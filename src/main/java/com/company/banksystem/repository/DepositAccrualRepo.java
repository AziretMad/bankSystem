package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.DepositAccrual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositAccrualRepo extends JpaRepository<DepositAccrual,Long> {
    DepositAccrual findAllByDeposit_Id(Long id);
}
