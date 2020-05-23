package com.company.banksystem.repository;

import com.company.banksystem.entity.actions.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepo extends JpaRepository<Exchange,Long> {
}
