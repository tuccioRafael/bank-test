package com.bank.test.services;

import com.bank.test.entities.Transfers;
import com.bank.test.repositories.TransferRepository;
import com.bank.test.services.dto.TransferRequest;
import com.bank.test.services.dto.TransferResponse;
import com.bank.test.services.dto.TransferUpdateRequest;
import com.bank.test.services.dto.TransfersDetailsResponse;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    @Autowired
    @InjectMocks
    TransferService transferService;

    @Mock
    TransferRepository transferRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    final TransferRequest transferRequest = new TransferRequest(
            "1231231231",
            "1231231233",
            BigDecimal.valueOf(100),
            LocalDate.of(2024,9,11)
    );
    final TransferUpdateRequest transferUpdateRequest = new TransferUpdateRequest(
            1L,
            "1231231231",
            "1231231233",
            BigDecimal.valueOf(150),
            LocalDate.now()
    );

    @Test
    @DisplayName("Shoud create transfers successfully when all is ok")
    void createTransfers() {

        Transfers transfer = new Transfers(transferRequest);
        when(transferRepository.save(any(Transfers.class))).thenReturn(transfer);
        TransfersDetailsResponse response = transferService.createTransfers(transferRequest);
        verify(transferRepository, times(1)).save(any(Transfers.class));
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(transfer.getId());
    }

    @Test
    @DisplayName("Should return 2 transfers when sucessfully")
    void testFindAllTransfers() {
        Pageable pageable = PageRequest.of(0, 10);
        Transfers transfer1 = new Transfers(transferRequest);
        Transfers transfer2 = new Transfers(transferRequest);
        Page<Transfers> transferPage = new PageImpl<>(List.of(transfer1, transfer2));
        when(transferRepository.findAllActive(pageable)).thenReturn(transferPage);

        Page<TransferResponse> responses = transferService.findAllTransfers(pageable);

        verify(transferRepository, times(1)).findAllActive(pageable);
        assertThat(responses.getContent()).hasSize(2);
    }

    @Test
    @DisplayName("Shold return update transfers when successfully")
    void testUpdateTransfer() {
        Transfers existingTransfer = new Transfers(transferRequest);
        existingTransfer.update(transferUpdateRequest);
        when(transferRepository.getReferenceById(transferUpdateRequest.id())).thenReturn(existingTransfer);
        TransfersDetailsResponse response = transferService.updateTransfer(transferUpdateRequest);
        verify(transferRepository, times(1)).getReferenceById(transferUpdateRequest.id());
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(existingTransfer.getId());
        assertThat(response.transferValue()).isEqualTo(BigDecimal.valueOf(150));
    }

    @Test
    @DisplayName("Shoul return deleted transfers when successfully")
    void testDeleteTransfer() {
        // Mock data
        Transfers transfer = new Transfers(transferRequest);
        when(transferRepository.getReferenceById(1L)).thenReturn(transfer);


        transferService.deleteTransfer(1L);


        verify(transferRepository, times(1)).getReferenceById(1L);
        Assertions.assertThat(transfer.getDeletedAt()).isNotNull();  // Assuming `softDelete` sets some deleted flag
    }

    @Test
    @DisplayName("should return ok when find transfer by id")
    void testGetTransfersById() {
        // Mock data
        Transfers transfer = new Transfers(transferRequest);
        when(transferRepository.getReferenceById(1L)).thenReturn(transfer);

        // Call the method
        TransfersDetailsResponse response = transferService.getTransfersById(1L);

        // Verify and assert
        verify(transferRepository, times(1)).getReferenceById(1L);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.id()).isEqualTo(transfer.getId());
    }
}