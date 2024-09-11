package com.bank.test.repositories;

import com.bank.test.entities.Transfers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferRepository extends JpaRepository<Transfers, Long> {

    @Query("SELECT t FROM Transfers t WHERE t.deletedAt IS NULL")
    Page<Transfers> findAllActive(Pageable pageable);
}
