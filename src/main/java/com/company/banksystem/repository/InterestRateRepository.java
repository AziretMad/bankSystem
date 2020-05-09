package com.company.banksystem.repository;

import com.company.banksystem.entity.InterestRateDeposit;
import com.company.banksystem.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRateDeposit,Long> {
    List<InterestRateDeposit> findAllByCurrency(Currency currency);
    List<InterestRateDeposit>findAllByDuration(Integer duration);
    Double findByCurrencyAndDuration(Currency currency,Integer duration);
}
