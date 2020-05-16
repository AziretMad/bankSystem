package com.company.banksystem.entity;

import com.company.banksystem.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="exchangeCurrency")
public class ExchangeCurrency  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="currency",unique = true)
    @Enumerated(value = EnumType.STRING)
    Currency currency;

    @Column(name="value")
    Double value;

}
