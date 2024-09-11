package com.bank.test.dto;
import com.bank.test.entities.Transfers;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransfersDetailsResponse(
        Long id,
        String originAccount,
        String destinationAccount,
        BigDecimal transferValue,
        BigDecimal rate,
        LocalDate transferDate,
        LocalDate createdAt,
        LocalDate updatedAt
) {
    public TransfersDetailsResponse(Transfers transfer) {
        this(
                transfer.getId(),
                transfer.getOriginAccount(),
                transfer.getDestinationAccount(),
                transfer.getTransferValue(),
                transfer.getRate(),
                transfer.getTransferDate(),
                transfer.getCreatedAt(),
                transfer.getUpdatedAt());
    }
}
