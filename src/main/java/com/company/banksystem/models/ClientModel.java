package com.company.banksystem.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ClientModel {
    String fullName;
    String telephone;
    String address;
    String inn;
}
