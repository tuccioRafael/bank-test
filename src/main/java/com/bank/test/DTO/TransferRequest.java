package com.bank.test.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferRequest(
        String originAccount,
        String destinationAccount,
        BigDecimal transferValue,
        LocalDate transferDate
        ) {
}
