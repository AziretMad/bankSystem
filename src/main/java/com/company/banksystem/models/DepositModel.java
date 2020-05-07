package com.company.banksystem.models;

import com.company.banksystem.entity.Client;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.DepositType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DepositModel {
    BigDecimal amount;
    Integer duration;
    DepositType depositType;
    Client client;
    Currency currency;
}
