package com.bank.test.repositories;

import com.bank.test.entities.Transfers;
import com.bank.test.services.dto.TransferRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class TransferRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TransferRepository transferRepository;

    @Test
    @DisplayName("Should return transfer successfylly")
    void findAllActive() {
        Pageable pageable = PageRequest.of(0, 10);
         TransferRequest transferRequest1 = new TransferRequest(
                "1231231231",
                "1231231233",
                BigDecimal.valueOf(100),
                LocalDate.of(2024,9,12)
        );
        TransferRequest transferRequest2 = new TransferRequest(
                "1231231231",
                "1231231233",
                BigDecimal.valueOf(100),
                LocalDate.of(2024,9,12)
        );
        TransferRequest transferRequest3 = new TransferRequest(
                "1231231231",
                "1231231233",
                BigDecimal.valueOf(100),
                LocalDate.of(2024,9,12)
        );
        this.createTransfer(transferRequest1);
        this.createTransfer(transferRequest2);
        this.createTransfer(transferRequest3);
        Page<Transfers> result = this.transferRepository.findAllActive(pageable);
        assertThat(result.getContent()).hasSize(3);
        assertThat(result.getContent()).allMatch(t -> t.getDeletedAt() == null);
    }

    @Test
    @DisplayName("Should not get transfer when transfer not exists")
    void findAllActiveEmpty() {
        Pageable pageable = PageRequest.of(0, 10);

        Page<Transfers> result = this.transferRepository.findAllActive(pageable);
        assertThat(result.getContent()).hasSize(0);
        assertThat(result.isEmpty()).isTrue();
    }

    private Transfers createTransfer(TransferRequest transferRequest) {
        Transfers transfer = new Transfers(transferRequest);
        this.entityManager.persist(transfer);
        return transfer;
    }

    @Test
    @DisplayName("Should return transfer successfylly")
    void findAllActiveSoftDelete() {
        Pageable pageable = PageRequest.of(0, 10);
        TransferRequest transferRequest1 = new TransferRequest(
                "1231231231",
                "1231231233",
                BigDecimal.valueOf(100),
                LocalDate.of(2024,9,12)
        );
        var transfer = this.createTransfer(transferRequest1);

        var onTransfer = this.transferRepository.getReferenceById(transfer.getId());
        this.deleteTransfer(onTransfer);

        Page<Transfers> result = this.transferRepository.findAllActive(pageable);

        assertThat(result.getContent()).hasSize(0);
        assertThat(result.getContent()).allMatch(t -> t.getDeletedAt() != null);
    }

    private void deleteTransfer(Transfers transfers) {
        transfers.softDelete();
    }
}