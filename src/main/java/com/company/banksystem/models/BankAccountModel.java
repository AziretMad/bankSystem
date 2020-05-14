package com.company.banksystem.models;

import com.company.banksystem.entity.Client;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BankAccountModel {
    BigDecimal amount;
    Currency currency;
    Status status;
    Client client;
    String codeWord;
}
