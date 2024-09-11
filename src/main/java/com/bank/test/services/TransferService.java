package com.bank.test.services;
import com.bank.test.dto.TransferRequest;
import com.bank.test.dto.TransferResponse;
import com.bank.test.dto.TransferUpdateRequest;
import com.bank.test.entities.Transfers;
import com.bank.test.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    @Transactional
    public void createTransfers (TransferRequest transfer) {
        repository.save(new Transfers(
                transfer.originAccount(),
                transfer.destinationAccount(),
                transfer.transferValue(),
                this.taxCalc(transfer.transferDate(), transfer.transferValue()),
                transfer.transferDate()
        ));
    }

    public Page<TransferResponse> findAllTransfers (Pageable pageable) {
        return repository.findAllActive(pageable).map(TransferResponse::new);
    }

    @Transactional
    public void updateTransfer(TransferUpdateRequest transferData) {
        var transfer = repository.getReferenceById(transferData.id());
        transfer.update(
                transferData.originAccount(),
                transferData.destinationAccount(),
                taxCalc(transferData.transferDate(), transferData.transferValue()),
                transferData.transferValue(),
                transferData.transferDate(),
                transferData.createdAt()
        );

    }

    @Transactional
    public void deleteTransfer(Long id) {
        var transfer = this.repository.getReferenceById(id);
        transfer.softDelete();
    }

    private BigDecimal taxCalc(LocalDate transferDate, BigDecimal transferValue) {
        return transferValue;
    }
}
