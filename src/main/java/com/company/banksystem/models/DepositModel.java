package com.company.banksystem.models;

import com.company.banksystem.entity.enums.DepositType;
import com.company.banksystem.entity.enums.Status;
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
public class DepositModel {
    Long id;
    String depositNumber;
    BigDecimal amount;
    Double interestRate;
    Date dateOfCreation;
    Date dateOfClosing;
    Integer duration;
    Status status;
    DepositType depositType;
    ClientModel clientModel;
}
