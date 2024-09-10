package com.bank.test.controllers;

import com.bank.test.dto.TransferRequest;
import com.bank.test.entities.Transfers;
import com.bank.test.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping
    public ResponseEntity<List<Transfers>> getAllTransfers() {
        List<Transfers> transfers = this.transferService.findAllTransfers();
        return ResponseEntity.ok(transfers);
    }

    @PostMapping
    public void createTransfers (@Valid @RequestBody TransferRequest transfer) {
        this.transferService.createTransfers(transfer);
    }
}
