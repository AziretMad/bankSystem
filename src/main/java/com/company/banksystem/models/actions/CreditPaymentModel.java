package com.company.banksystem.models.actions;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.enums.CreditPaymentType;
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
public class CreditPaymentModel {
    Long id;
    Credit credit;
    BigDecimal amount;
    CreditPaymentType paymentType; //Аннуитентный или дифференцированный
    Date date;
}
