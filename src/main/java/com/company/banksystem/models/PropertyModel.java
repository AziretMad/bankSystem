package com.company.banksystem.models;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Credit;
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
    BigDecimal marketPrice;
    String description;
    Long client;
    Long credit;
}
