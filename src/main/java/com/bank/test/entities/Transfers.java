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

    private String originAccount;
    private String destinationAccount;
    private BigDecimal transferValue;
    private BigDecimal rate;
    private LocalDate createdAt;
    private LocalDate transferDate;

    public Transfers(String originAccount, String destinationAccount, BigDecimal transferValue, BigDecimal rate, LocalDate createdAt, LocalDate transferDate) {
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.transferValue = transferValue;
        this.rate = rate;
        this.createdAt = createdAt;
        this.transferDate = transferDate;
    }

}
