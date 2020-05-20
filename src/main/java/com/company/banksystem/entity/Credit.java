package com.company.banksystem.entity;

import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.Status;
import com.company.banksystem.enums.CreditPaymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "credit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "credit_number")
    String creditNumber;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "interest_rate")
    Double interestRate;

    @CreatedDate
    @Column(name = "created_date")
    @Builder.Default
    Date createdDate=new Date();

    @Column(name = "closed_date")
    Date closedDate;

    @Column(name = "duration")
    Integer duration;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @Enumerated
    @Column(name="status")
    Status status;

    @Enumerated
    @Column(name="creditType")
    CreditPaymentType creditType;

    @Enumerated(EnumType.STRING)
    @Column(name="currency")
    Currency currency;
}
