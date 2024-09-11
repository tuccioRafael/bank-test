package com.bank.test.controllers;

import com.bank.test.dto.TransferRequest;
import com.bank.test.dto.TransferResponse;
import com.bank.test.dto.TransferUpdateRequest;
import com.bank.test.repositories.TransferRepository;
import com.bank.test.services.TransferService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;
    private TransferRepository repository;

    @GetMapping
    public Page<TransferResponse> getAllTransfers(Pageable pageable) {
        return this.transferService.findAllTransfers(pageable);
    }

    @GetMapping("/{id}")
    public void getTransferById(@PathVariable Long id) {
        System.out.println(id);
    }

    @PostMapping
    public void createTransfers (@Valid @RequestBody TransferRequest transfer) {
        this.transferService.createTransfers(transfer);
    }

    @PutMapping()
    public void updateTransfer (@Valid @RequestBody TransferUpdateRequest transfer) {
        this.transferService.updateTransfer(transfer);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        this.transferService.deleteTransfer(id);
    }
}
