package com.bank.test.dto;

import com.bank.test.constraints.FutureOrTodayValidate;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferRequest(
        @NotBlank(message = "A conta de origem não pode ser vazia.")
        @Pattern(regexp = "^\\d{10}$", message = "Número da conta origem invalido.")
        String originAccount,

        @NotBlank(message = "A conta de destino não pode ser vazia.")
        @Pattern(regexp = "^\\d{10}$", message = "Número da conta destino invalido.")
        String destinationAccount,

        @NotNull(message = "O valor não pode ser vazio.")
        @Positive(message = "O valor da transferencia tem que ser maior do que zero.")
        BigDecimal transferValue,

        @NotNull(message = "A data de transferencia não pode ser vazia.")
        @FutureOrTodayValidate
        LocalDate transferDate
        ) { }
