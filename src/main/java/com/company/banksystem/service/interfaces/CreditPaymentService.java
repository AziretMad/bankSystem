package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.actions.CreditPayment;
import com.company.banksystem.models.actions.CreditPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditPaymentService extends BaseService<CreditPayment, CreditPaymentModel> {
}
