package com.company.banksystem.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "telephone")
    String telephone;

    @Column(name = "address")
    String address;

    @OneToMany
    @JoinColumn(name = "bank_account_id")
    List<BankAccount> bankAccounts;

    @OneToMany
    @JoinColumn(name = "deposit_id")
    List<Deposit> deposits;

    @OneToMany
    @JoinColumn(name = "credit_id")
    List<Credit> credits;

    @OneToMany
    @JoinColumn(name = "property_id")
    List<Property> properties;
}
