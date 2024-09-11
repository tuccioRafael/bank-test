package com.bank.test.services;
import com.bank.test.services.dto.TransferRequest;
import com.bank.test.services.dto.TransferResponse;
import com.bank.test.services.dto.TransferUpdateRequest;
import com.bank.test.entities.Transfers;
import com.bank.test.repositories.TransferRepository;
import com.bank.test.services.dto.TransfersDetailsResponse;
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
    public TransfersDetailsResponse createTransfers(TransferRequest transferData) {
        var transfer = new Transfers(transferData);
        repository.save(transfer);
        return new TransfersDetailsResponse(transfer);
    }

    public Page<TransferResponse> findAllTransfers(Pageable pageable) {
        return repository.findAllActive(pageable).map(TransferResponse::new);
    }

    @Transactional
    public TransfersDetailsResponse updateTransfer(TransferUpdateRequest transferData) {
        var transfer = repository.getReferenceById(transferData.id());
        transfer.update(transferData);
        return new TransfersDetailsResponse(transfer);
    }

    @Transactional
    public void deleteTransfer(Long id) {
        var transfer = this.repository.getReferenceById(id);
        transfer.softDelete();
    }

    public TransfersDetailsResponse getTransfersById(Long id) {
        return new TransfersDetailsResponse(this.repository.getReferenceById(id));
    }

}