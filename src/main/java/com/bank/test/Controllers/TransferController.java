package com.bank.test.Controllers;

import com.bank.test.DTO.TransferRequest;
import com.bank.test.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferRepository transferRepository;

    @PostMapping
    public void createTransfers (@RequestBody TransferRequest transfer) {
        System.out.println(transfer);
    }
}
