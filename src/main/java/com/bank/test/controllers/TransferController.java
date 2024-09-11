package com.bank.test.controllers;
import com.bank.test.dto.TransferRequest;
import com.bank.test.dto.TransferResponse;
import com.bank.test.dto.TransferUpdateRequest;
import com.bank.test.dto.TransfersDetailsResponse;
import com.bank.test.repositories.TransferRepository;
import com.bank.test.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;
    private TransferRepository repository;

    @GetMapping
    public ResponseEntity<Page<TransferResponse>> getAllTransfers(Pageable pageable) {
        var transfers = this.transferService.findAllTransfers(pageable);
        return ResponseEntity.ok(transfers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransfersDetailsResponse> getTransferDetails(@PathVariable Long id) {
        var transfer = this.transferService.getTransfersById(id);
        return ResponseEntity.ok(new TransfersDetailsResponse(transfer));
    }

    @PostMapping
    public ResponseEntity<TransfersDetailsResponse> createTransfers (@Valid @RequestBody TransferRequest transferData, UriComponentsBuilder uriBuilder) {
        var trasnfer = this.transferService.createTransfers(transferData);
        var uri = uriBuilder.path("/transfers/{id}").buildAndExpand(trasnfer.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransfersDetailsResponse(trasnfer));
    }

    @PutMapping()
    public ResponseEntity<TransfersDetailsResponse> updateTransfer (@Valid @RequestBody TransferUpdateRequest transferData) {
        var transfer = this.transferService.updateTransfer(transferData);
        return ResponseEntity.ok(new TransfersDetailsResponse(transfer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        this.transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }
}
