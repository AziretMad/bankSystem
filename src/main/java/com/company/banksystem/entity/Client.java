package com.company.banksystem.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "client_roles")
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

    @Column(name="inn")
    String inn;
}
