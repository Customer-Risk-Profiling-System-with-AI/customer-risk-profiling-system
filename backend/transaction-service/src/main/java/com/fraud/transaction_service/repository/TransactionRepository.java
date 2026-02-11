package com.fraud.transaction_service.repository;

import com.fraud.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List; //Collection (group) of items
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    //📌 List = many
    //📌 Optional = zero or one
    //“Query by Method Name”
    List<Transaction> findByCustomerId(Long customerId);
    Optional<Transaction> findByTransactionId(Long transactionId);

    List<Transaction> findByTransactionDatetimeBetween(LocalDateTime start, LocalDateTime end);

    List<Transaction> findByTransactionStatus(String transactionStatus);
}