package com.bank.test.services;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTransferTest {

    @Test
    void testCalculateRateForSameDayTransfer() {
        BigDecimal transferValue = BigDecimal.valueOf(1000);
        LocalDate transferDate = LocalDate.now();

        BigDecimal expectedRate = BigDecimal.valueOf(28.00); // 2.5% + 3 adicional
        BigDecimal actualRate = CalculatorTransfer.calculateRate(transferValue, transferDate);

        assertEquals(expectedRate.setScale(2, RoundingMode.HALF_UP), actualRate);
    }

    @Test
    void testCalculateRateForTransferBetween01And10Days() {
        BigDecimal transferValue = BigDecimal.valueOf(1000);
        LocalDate transferDate = LocalDate.now().plusDays(4);
        BigDecimal expectedRate = BigDecimal.valueOf(12.00); //0% + 12.00 adicional
        BigDecimal actualRate = CalculatorTransfer.calculateRate(transferValue, transferDate);
        assertEquals(expectedRate.setScale(2, RoundingMode.HALF_UP), actualRate);
    }

    @Test
    void testCalculateRateForTransferBetween11And20Days() {
        BigDecimal transferValue = BigDecimal.valueOf(1000);
        LocalDate transferDate = LocalDate.now().plusDays(15);

        BigDecimal expectedRate = BigDecimal.valueOf(82.00); // 8.2% and no additional
        BigDecimal actualRate = CalculatorTransfer.calculateRate(transferValue, transferDate);

        assertEquals(expectedRate.setScale(2, RoundingMode.HALF_UP), actualRate);
    }

    @Test
    void testCalculateRateForTransferBetween21And30Days() {
        BigDecimal transferValue = BigDecimal.valueOf(1000);
        LocalDate transferDate = LocalDate.now().plusDays(25);

        BigDecimal expectedRate = BigDecimal.valueOf(69.00); // 6.9% and no additional
        BigDecimal actualRate = CalculatorTransfer.calculateRate(transferValue, transferDate);

        assertEquals(expectedRate.setScale(2, RoundingMode.HALF_UP), actualRate);
    }

    @Test
    void testCalculateRateForTransferBetween31And40Days() {
        BigDecimal transferValue = BigDecimal.valueOf(1000);
        LocalDate transferDate = LocalDate.now().plusDays(35);

        BigDecimal expectedRate = BigDecimal.valueOf(45.70); // 4.57% and no additional
        BigDecimal actualRate = CalculatorTransfer.calculateRate(transferValue, transferDate);

        assertEquals(expectedRate.setScale(2, RoundingMode.HALF_UP), actualRate);
    }

    @Test
    void testCalculateRateForTransferOver40Days() {
        BigDecimal transferValue = BigDecimal.valueOf(1000);
        LocalDate transferDate = LocalDate.now().plusDays(50);

        BigDecimal expectedRate = BigDecimal.valueOf(17.00); // 1.7% and no additional
        BigDecimal actualRate = CalculatorTransfer.calculateRate(transferValue, transferDate);

        assertEquals(expectedRate.setScale(2, RoundingMode.HALF_UP), actualRate);
    }
}
