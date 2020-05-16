package com.company.banksystem.models;

import com.company.banksystem.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ExchangeCurrencyModel {
    Currency currency;
    Double value;
}
