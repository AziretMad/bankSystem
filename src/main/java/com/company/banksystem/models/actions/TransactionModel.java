package com.company.banksystem.models.actions;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.enums.Currency;
import com.company.banksystem.entity.enums.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionModel {
    Long id;
    Date createdDate = new Date();
    BigDecimal amount;
    BankAccount accountFrom;
    BankAccount accountTo;
    TransactionStatus status;
    Currency currency;
}
