package com.bank.test.dto;

import com.bank.test.constraints.FutureOrTodayValidate;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferRequest(
        @NotBlank
        @Pattern(regexp = "^\\d{10}$", message = "Número da conta origem invalido.")
        String originAccount,

        @NotBlank
        @Pattern(regexp = "^\\d{10}$", message = "Número da conta origem invalido.")
        String destinationAccount,

        @Positive(message = "O valor da transferencia tem que ser maior do que zero")
        BigDecimal transferValue,

        @FutureOrTodayValidate
        LocalDate transferDate
        ) {
}
