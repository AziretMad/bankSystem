package com.company.banksystem.entity;

import com.company.banksystem.enums.DepositType;
import com.company.banksystem.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "deposit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "deposit_number")
    String depositNumber;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "interest_rate")
    Double interestRate;

    @CreatedDate
    @Column(name = "date_of_creation")
    Date dateOfCreation=new Date();

    @Column(name = "date_of_closing")
    Date dateOfClosing;

    @Column(name = "duration")
    Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_type")
    DepositType depositType;

    @ManyToOne
    @JoinColumn(name="client_id")
    Client client;
}
