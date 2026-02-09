package com.fraud.transaction_service.service;

import com.fraud.transaction_service.entity.Transaction;
import org.springframework.stereotype.Service;
import com.fraud.transaction_service.repository.TransactionRepository;
import com.fraud.transaction_service.dto.*;

@Service
public class TransactionService {

    private TransactionRepository transactionrepository;

    public TransactionService(TransactionRepository transactionrepository){
        this.transactionrepository = transactionrepository;
    }

    public TransactionResponse transaction_save(TransactionRequest request){

        Transaction ts = new Transaction();
        ts.setCustomerId(request.getCustomerId());
        ts.setAmount(request.getAmount());
        ts.setCurrency(request.getCurrency());
        ts.setChannel(request.getChannel());
        ts.setMerchantCode(request.getMerchantCode());
        ts.setDeviceCode(request.getDeviceCode());
        ts.setLocation(request.getLocation());


    }

}
