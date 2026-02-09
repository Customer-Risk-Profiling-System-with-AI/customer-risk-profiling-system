package com.fraud.transaction_service.repository;

<<<<<<< HEAD
import com.fraud.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List; //Collection (group) of items

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    //📌 List = many
    //📌 Optional = zero or one
    //“Query by Method Name”
    List<Transaction> findByCustomerId(String customerId);
    List<Transaction> findByTransactionDatetimeBetween(LocalDateTime start, LocalDateTime end);
    List<Transaction> findByTransactionStatus(String transactionStatus);
=======
public class TransactionRepository {
>>>>>>> upstream/backend-dev
}
