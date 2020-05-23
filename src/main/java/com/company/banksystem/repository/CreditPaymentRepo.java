package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.CreditPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditPaymentRepo extends JpaRepository<CreditPayment,Long> {
    @Query("select count(id) from CreditPayment where credit.id = :credit_id")
    Integer countAllPaymentsByCredit(@Param("credit_id") Long id);

    @Query("select payment from CreditPayment payment where payment.credit.id = :id")
    List<CreditPayment> getCreditPaymentsByCreditId(@Param("id") Long id);

    @Query("select payment from CreditPayment payment where payment.credit.creditNumber = :number")
    List<CreditPayment> getCreditPaymentsByCreditNumber(@Param("number") String number);
}
