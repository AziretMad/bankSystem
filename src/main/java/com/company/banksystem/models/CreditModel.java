package com.company.banksystem.models;

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
public class CreditModel {
    Long id;
    String creditNumber;
    BigDecimal amount;
    Double interestRate;
    Date dateOfCreation;
    Date dateOfClosing;
    Integer duration;
    ClientModel clientModel;
}
