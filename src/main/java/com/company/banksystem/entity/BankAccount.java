package com.company.banksystem.entity;

import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bank_account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "account_number")
    String accountNumber;

    @Column(name = "amount")
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    Currency currency;

    @CreatedDate
    @Column(name = "created_date")
    Date createdDate=new Date();

    @Column(name = "closed_date")
    Date closedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @ManyToOne
    @JoinColumn(name="client_id")
    Client client;

    @Column(name="codeWord")
    String codeWord;
}
