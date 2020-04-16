package com.company.banksystem.entity.actions;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.enums.CreditPaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposit_accrual")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepositAccrual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "deposit_id")
    Deposit deposit;

    @Column(name = "amount")
    BigDecimal amount;
}
