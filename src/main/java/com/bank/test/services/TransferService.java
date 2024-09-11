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

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    @Transactional
    public Transfers createTransfers(TransferRequest transferData) {
        var transfer = new Transfers(transferData);
        repository.save(transfer);
        return transfer;
    }

    public Page<TransferResponse> findAllTransfers(Pageable pageable) {
        return repository.findAllActive(pageable).map(TransferResponse::new);
    }

    @Transactional
    public Transfers updateTransfer(TransferUpdateRequest transferData) {
        var transfer = repository.getReferenceById(transferData.id());
        transfer.update(transferData);
        return transfer;
    }

    @Transactional
    public void deleteTransfer(Long id) {
        var transfer = this.repository.getReferenceById(id);
        transfer.softDelete();
    }

    public Transfers getTransfersById(Long id) {
        return this.repository.getReferenceById(id);
    }

}