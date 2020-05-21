package com.company.banksystem.entity.actions;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PaymentCalculator")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PaymentCalculator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "amount")
    BigDecimal amount;
}
