package com.company.banksystem.models;

import com.company.banksystem.entity.Client;
import com.company.banksystem.enums.Currency;
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
    Client client;
    String codeWord;
}
