package com.company.banksystem.entity.actions;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.enums.CreditPaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "credit_payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    Credit credit;

    @Column(name = "amount")
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    CreditPaymentType paymentType;
}
