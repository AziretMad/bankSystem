package com.company.banksystem.entity.actions;

import com.company.banksystem.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "exchange")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "currencyFrom")
    Currency currencyFrom;
    @Column(name = "currencyTo")
    Currency currencyTo;
    @Column(name = "amount")
    BigDecimal amount;
    @Column(name="exchangeAmount")
    BigDecimal exchangeAmount;
}
