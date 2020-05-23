package com.company.banksystem.repository;

import com.company.banksystem.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepo extends JpaRepository<Deposit,Long> {
    @Query("select deposit from Deposit deposit where deposit.depositNumber = :number")
    Deposit findDepositByDepositNumber(@Param("number") String number);

    @Query("select deposit from Deposit deposit where deposit.client.id = :id")
    List<Deposit> findAllDepositsByClientId(@Param("id") Long id);
}
