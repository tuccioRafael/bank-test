package com.bank.test.services;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculatorTransfer {

    public static BigDecimal calculateRate(BigDecimal transferValue, LocalDate transferDate) {
        int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), transferDate);
        var rate = getRateByDay(days);
        return transferValue
                .multiply(rate.divide(BigDecimal.valueOf(100)))
                .add(additionalValue(days)).setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal getRateByDay(int days) {
        if(days == 0) return BigDecimal.valueOf(2.5);
        if(days >= 1 && days <= 10) return BigDecimal.ZERO;
        if(days >= 11 && days <= 20) return BigDecimal.valueOf(8.2);
        if(days >= 21 && days <= 30) return BigDecimal.valueOf(6.9);
        if(days >= 31 && days <= 40) return BigDecimal.valueOf(4.57);
        return BigDecimal.valueOf(1.7);
    }

    private static BigDecimal additionalValue(int days) {
        if(days == 0) return BigDecimal.valueOf(3);
        if(days >= 1 && days <= 10) return BigDecimal.valueOf(12);
        return BigDecimal.ZERO;
    }


}
