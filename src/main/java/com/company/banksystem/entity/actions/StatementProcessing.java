package com.company.banksystem.entity.actions;

import com.company.banksystem.enums.StatementType;
import com.company.banksystem.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="statement_process")
public class StatementProcessing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="statement_id")
    Long statementId;

    @Column(name="status")
    Status status;

    @Column(name="reason")
    String reason;

    @Enumerated
    @Column(name="statementType")
    StatementType statementType;

}
