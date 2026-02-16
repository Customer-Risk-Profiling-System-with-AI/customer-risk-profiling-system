package com.fraud.transaction_service.service;


import com.fraud.transaction_service.entity.Transaction;
import com.fraud.transaction_service.Channel.TransactionStatus;
import com.fraud.transaction_service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.fraud.transaction_service.repository.TransactionRepository;
import com.fraud.transaction_service.dto.*;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionResponse create(TransactionRequest request) {

        /*Transaction ts = new Transaction();
        ts.setCustomerId(request.getCustomerId());
        ts.setAmount(request.getAmount());
        ts.setCurrency(request.getCurrency());
        ts.setChannel(request.getChannel());
        ts.setMerchantCode(request.getMerchantCode());
        ts.setDeviceCode(request.getDeviceCode());
        ts.setLocation(request.getLocation());
        ts.setTransactionStatus(TransactionStatus.PENDING);*/

        Transaction ts = Transaction.builder()
                .customerId(request.getCustomerId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .channel(request.getChannel())
                .merchantCode(request.getMerchantCode())
                .deviceCode(request.getDeviceCode())
                .location(request.getLocation())
                .transactionStatus(TransactionStatus.PENDING)
                .build();

        Transaction saved = transactionRepository.save(ts);

        return map(saved); //Convert a Transaction entity into a TransactionResponse DTO.
    }

    private TransactionResponse map(Transaction ts){
        return TransactionResponse.builder()
                .transactionId(ts.getTransactionId())
                .customerId(ts.getCustomerId())
                .amount(ts.getAmount())
                .currency(ts.getCurrency())
                .channel(ts.getChannel())
                .merchantCode(ts.getMerchantCode())
                .deviceCode(ts.getDeviceCode())
                .location(ts.getLocation())
                .transactionStatus(ts.getTransactionStatus())
                .declineReason(ts.getDeclineReason())
                .transactionDatetime(ts.getTransactionDatetime())
                .createdAt(ts.getCreatedAt())
                .build();

    }

    public List<Transaction> getByCustomerId(Long customerId){
        return transactionRepository.findByCustomerId(customerId);
    }

    public List<TransactionResponse> getApproved(Long customerId){
        return transactionRepository.findByCustomerId(customerId)
                .stream() //Start Reading the List One by One
                .filter(tx -> tx.getTransactionStatus() == TransactionStatus.APPROVED)
                .map(this::map)
                .toList();
    }

    public TransactionResponse getByTransactionId(Long transactionId){
        Transaction ts = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(()-> new ResourceNotFoundException("Transaction not found:" + transactionId));
        return map(ts);
    }

    public TransactionResponse updateStatus(Long transactionId, TransactionStatus status, String declineReason) {
        Transaction tx = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + transactionId));

        tx.setTransactionStatus(status);
        tx.setDeclineReason(declineReason);

        Transaction saved = transactionRepository.save(tx);
        return map(saved);
    }

}
