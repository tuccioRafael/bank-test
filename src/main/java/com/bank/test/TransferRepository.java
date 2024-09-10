package com.bank.test;

import com.bank.test.Entities.Transfers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfers, Long> {
}
