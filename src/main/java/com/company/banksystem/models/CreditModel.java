package com.company.banksystem.models;

import com.company.banksystem.enums.CreditPaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CreditModel {
    Long id;
    String creditNumber;
    BigDecimal amount;
    Double interestRate;
    Integer duration;
    ClientModel clientModel;
    CreditPaymentType creditType;
}
