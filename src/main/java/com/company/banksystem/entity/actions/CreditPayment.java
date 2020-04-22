package com.company.banksystem.entity.actions;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.enums.CreditPaymentType;
import com.company.banksystem.entity.enums.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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
    CreditPaymentType paymentType; //Аннуитентный или дифференцированный

    @CreatedDate
    @Column(name = "date")
    Date date=new Date();

    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    TransactionStatus status;
}
