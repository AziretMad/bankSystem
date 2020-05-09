package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditPaymentRepo extends JpaRepository<CreditPayment,Long> {
    @Query("select count(id) from CreditPayment where credit.id = :credit_id")
    Integer findAllPaymentsByCredit(@Param("credit_id") Long id);
}
