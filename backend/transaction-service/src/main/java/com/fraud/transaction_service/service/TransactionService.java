package com.fraud.transaction_service.service;


import com.fraud.transaction_service.entity.Transaction;
import com.fraud.transaction_service.entity.TransactionStatus;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Service;
import com.fraud.transaction_service.repository.TransactionRepository;
import com.fraud.transaction_service.dto.*;

@Service
public class TransactionService {

    private TransactionRepository transactionrepository;

    public TransactionService(TransactionRepository transactionrepository) {
        this.transactionrepository = transactionrepository;
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

        Transaction saved = transactionrepository.save(ts);

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

}
