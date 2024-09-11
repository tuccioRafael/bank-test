package com.bank.test.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "Transfers")
@Table(name = "transfers")
@NoArgsConstructor
@AllArgsConstructor
public class Transfers {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String originAccount;

    @Column(nullable = false)
    private String destinationAccount;

    @Column(nullable = false)
    private BigDecimal transferValue;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDate transferDate;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false, updatable = false)
    private LocalDate updatedAt;

    @Column
    private LocalDate deletedAt;

    public Transfers(
            String originAccount,
            String destinationAccount,
            BigDecimal transferValue,
            BigDecimal rate,
            LocalDate transferDate) {

        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.transferValue = transferValue;
        this.rate = rate;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.transferDate = transferDate;
    }

    public void update(String originAccount, String destinationAccount, BigDecimal transferValue, BigDecimal rate, LocalDate transferDate, LocalDate createdAt) {
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.rate = rate;
        this.transferValue = transferValue;
        this.transferDate = transferDate;
        this.createdAt = createdAt;
        this.updatedAt = LocalDate.now();
    }

    public void softDelete() {
        this.deletedAt = LocalDate.now();
    }


}
