package com.company.banksystem.repository;

import com.company.banksystem.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepo extends JpaRepository<Credit,Long> {
    @Query("select credit from Credit credit where credit.creditNumber = :number")
    Credit findCreditByCreditNumber(@Param("number") String number);

    @Query("select credit from Credit credit where credit.client.id = :id")
    List<Credit> findAllCreditsByClientId(@Param("id") Long id);
}
