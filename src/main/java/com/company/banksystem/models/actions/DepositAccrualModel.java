package com.company.banksystem.models.actions;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.Deposit;
import lombok.*;
import lombok.experimental.FieldDefaults;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DepositAccrualModel {
    Deposit deposit;
    BankAccount bankAccount;
    //because other properties we are generate

}
