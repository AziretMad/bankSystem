package com.company.banksystem.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    @Column(name = "date_of_creation")
    Date dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @Column(name = "date_of_closing")
    Date dateOfClosing;

    @Column(name = "duration")
    Integer duration;

    @OneToMany
    @JoinColumn(name = "property_id")
    List<Property> properties;
}
