package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.DepositAccrual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositAccrualRepo extends JpaRepository<DepositAccrual,Long> {
    @Query("select d from DepositAccrual d where d.deposit.id=:id")
    DepositAccrual getByDepositId(@Param("id") Long id);
}
