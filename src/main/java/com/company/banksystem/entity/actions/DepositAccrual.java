package com.company.banksystem.entity.actions;

import com.company.banksystem.entity.Deposit;
import com.company.banksystem.enums.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

    @CreatedDate
    @Column(name = "date")
    Date date = new Date();

    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    TransactionStatus status;
}
