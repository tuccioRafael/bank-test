package com.bank.test.services.dto;

import com.bank.test.constraints.FutureOrTodayValidate;
import com.bank.test.constraints.TransferDateValidate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferUpdateRequest(
        @NotNull
        Long id,

        @Pattern(regexp = "^\\d{10}$", message = "Número da conta origem invalido.")
        String originAccount,

        @Pattern(regexp = "^\\d{10}$", message = "Número da conta destino invalido.")
        String destinationAccount,

        @NotNull(message = "O valor não pode ser vazio.")
        @Positive(message = "O valor da transferencia tem que ser maior do que zero.")
        BigDecimal transferValue,

        @NotNull(message = "A data de transferencia não pode ser vazia.")
        @TransferDateValidate
        @FutureOrTodayValidate
        LocalDate transferDate
        ) {
}
