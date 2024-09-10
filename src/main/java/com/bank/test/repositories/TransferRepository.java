package com.bank.test.repositories;

import com.bank.test.entities.Transfers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfers, Long> {
}
