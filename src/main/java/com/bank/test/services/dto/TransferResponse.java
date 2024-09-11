package com.bank.test.services.dto;

import com.bank.test.entities.Transfers;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferResponse(
        Long id,
        String originAccount,
        String destinationAccount,
        BigDecimal transferValue,
        BigDecimal rate,
        LocalDate transferDate,
        LocalDate createdAt
) {

    public TransferResponse (Transfers transfers) {
        this(transfers.getId(),
                transfers.getOriginAccount(),
                transfers.getDestinationAccount(),
                transfers.getTransferValue(),
                transfers.getRate(),
                transfers.getTransferDate(),
                transfers.getCreatedAt());
    }
}
