package com.company.banksystem.models;

import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BankAccountModel {
    Long id;
    String accountNumber;
    BigDecimal amount;
    Currency currency;
    Date dateOfCreation;
    Date dateOfClosing;
    Status status;
    ClientModel clientModel;
    String codeWord;
}
