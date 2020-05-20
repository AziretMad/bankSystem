package com.company.banksystem.models.actions;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionModel {
    BigDecimal amount;
    BankAccount accountFrom;
    BankAccount accountTo;
    Currency currency;
}
