package com.bank.test.Controllers;

import com.bank.test.DTO.TransferRequest;
import com.bank.test.Entities.Transfers;
import com.bank.test.Services.TransferService;
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
    public void createTransfers (@RequestBody TransferRequest transfer) {
        this.transferService.createTransfers(transfer);
    }
}
