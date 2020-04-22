package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditPaymentRepo extends JpaRepository<CreditPayment,Long> {
}
