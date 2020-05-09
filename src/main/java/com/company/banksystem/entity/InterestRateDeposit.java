package com.company.banksystem.entity;

import com.company.banksystem.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="interestRateDeposit",
        uniqueConstraints = @UniqueConstraint(columnNames = {"duration","currency"}))
public class InterestRateDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="duration")
    Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    Currency currency;

    @Column(name="interestRate")
    Double interestRate;
}
