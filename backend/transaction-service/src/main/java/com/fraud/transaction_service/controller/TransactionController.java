package com.fraud.transaction_service.controller;

import com.fraud.transaction_service.dto.TransactionRequest;
import com.fraud.transaction_service.dto.TransactionResponse;
import com.fraud.transaction_service.entity.Transaction;
import com.fraud.transaction_service.Channel.TransactionStatus;
import com.fraud.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // 1️⃣ Create transaction
    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest request) {
        return transactionService.create(request);
    }

    // 2️⃣ Get transaction by ID
    @GetMapping("/{transactionId}")
    public TransactionResponse getById(@PathVariable Long transactionId) {
        return transactionService.getByTransactionId(transactionId);
    }

    // 3️⃣ Get all transactions of customer
    @GetMapping("/customer/{customerId}")
    public List<Transaction> getByCustomer(@PathVariable Long customerId) {
        return transactionService.getByCustomerId(customerId);
    }

    // 4️⃣ Get approved transactions
    @GetMapping("/customer/{customerId}/approved")
    public List<TransactionResponse> getApproved(@PathVariable Long customerId) {
        return transactionService.getApproved(customerId);
    }

    // 5️⃣ Update transaction status (approve/decline)
    @PutMapping("/{transactionId}/status")
    public TransactionResponse updateStatus(
            @PathVariable Long transactionId,
            @RequestParam TransactionStatus status,
            @RequestParam(required = false) String reason
    ) {
        return transactionService.updateStatus(transactionId, status, reason);
    }
}