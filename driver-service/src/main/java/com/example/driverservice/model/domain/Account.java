package com.example.driverservice.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "red_dollar_balance")
    private BigDecimal redDollarBalance;

    @Column(name = "green_dollar_balance")
    private BigDecimal greenDollarBalance;

    @Column(name = "blue_dollar_balance")
    private BigDecimal blueDollarBalance;

    @OneToOne(mappedBy = "account")
    private Driver driver;
}
