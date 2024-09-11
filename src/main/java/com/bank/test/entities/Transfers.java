package com.bank.test.entities;


import com.bank.test.dto.TransferRequest;
import com.bank.test.dto.TransferUpdateRequest;
import com.bank.test.services.CalculatorTransfer;
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

    public Transfers(TransferRequest transferData) {
        this.originAccount = transferData.originAccount();
        this.destinationAccount = transferData.destinationAccount();
        this.transferValue = transferData.transferValue();
        this.rate = CalculatorTransfer.calculateRate(transferData.transferValue(), transferData.transferDate());
        this.transferDate = transferData.transferDate();
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public void update(TransferUpdateRequest transferData) {
        this.originAccount = transferData.originAccount();
        this.destinationAccount = transferData.destinationAccount();
        this.rate = CalculatorTransfer.calculateRate(transferData.transferValue(), transferData.transferDate());
        this.transferValue = transferData.transferValue();
        this.transferDate = transferData.transferDate();
        this.createdAt = transferData.createdAt();
        this.updatedAt = LocalDate.now();
    }

    public void softDelete() {
        this.deletedAt = LocalDate.now();
    }


}
