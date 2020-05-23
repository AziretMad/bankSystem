package com.company.banksystem.models.actions;

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
public class ExchangeModel {
    Currency currencyFrom;
    Currency currencyTo;
    BigDecimal amount;
}
