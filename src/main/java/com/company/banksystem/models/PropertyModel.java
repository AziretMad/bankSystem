package com.company.banksystem.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PropertyModel {
    Long id;
    BigDecimal marketPrice;
    String description;
    ClientModel clientModel;
    CreditModel creditModel;
}
