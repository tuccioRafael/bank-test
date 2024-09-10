package com.bank.test.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Transfers {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String originAccount;
    private String destinationAccount;
    private BigDecimal transferValue;
    private BigDecimal rate;
    private LocalDate createdAt;
    private LocalDate transferDate;
}
