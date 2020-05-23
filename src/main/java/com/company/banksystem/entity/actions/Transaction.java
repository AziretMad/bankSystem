package com.company.banksystem.entity.actions;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.enums.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @CreatedDate
    @Column(name = "created_date")
    @Builder.Default
    Date createdDate = new Date();

    @Column(name = "amount")
    BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_from_id")
    BankAccount accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to_id")
    BankAccount accountTo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    TransactionStatus status;

    @Column(name="code")
    Integer code;
}
