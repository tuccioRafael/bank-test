package com.bank.test.Services;

import com.bank.test.DTO.TransferRequest;
import com.bank.test.Entities.Transfers;
import com.bank.test.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    public void createTransfers (TransferRequest transfer) {
        repository.save(new Transfers(
                transfer.originAccount(),
                transfer.destinationAccount(),
                transfer.transferValue(),
                this.taxCalc(transfer.transferDate(), transfer.transferValue()),
                transfer.transferDate(),
                LocalDate.now()
        ));
    }

    public List<Transfers> findAllTransfers () {
        return repository.findAll();
    }

    private BigDecimal taxCalc(LocalDate transferDate, BigDecimal transferValue) {
        return transferValue;
    }
}
