package com.company.banksystem.models.actions;

import com.company.banksystem.entity.Deposit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DepositAccrualModel {
    Integer id;
    Deposit deposit;
    BigDecimal amount;
}
