package com.company.banksystem.repository;

import com.company.banksystem.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount,Long> {
    @Query("select bankAccount from BankAccount bankAccount where bankAccount.accountNumber = :number")
    BankAccount findBankAccountByAccountNumber(@Param("number") String number);

    @Query("select bankAccount from BankAccount bankAccount where bankAccount.client.id = :id")
    List<BankAccount> findBankAccountsByClientId(@Param("id") Long id);
}
