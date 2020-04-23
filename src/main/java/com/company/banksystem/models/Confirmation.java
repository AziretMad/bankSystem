package com.company.banksystem.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Confirmation {
    Long transactionId;
    Integer confirmationCode;
}
