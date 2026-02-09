package com.fraud.transaction_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data //Automatically generates: ✔ Getters ✔ Setters ✔ toString() ✔ equals() / hashCode() ✔ Required constructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;   // FK

    @Column(name = "transaction_datetime", nullable = false)
    private LocalDateTime transactionDatetime;

    @Column(name = "amount", nullable = false)
    private Double amount;     // you can change to BigDecimal later if you want

    @Column(name = "currency", length = 10)
    private String currency;

    @Column(name = "channel", length = 20)
    private String channel;    // POS / ATM / ECOM

    @Column(name = "merchant_code")
    private String merchantCode;   // FK

    @Column(name = "device_code")
    private String deviceCode;     // FK

    @Column(name = "location")
    private String location;   // FK

    @Column(name = "transaction_status", length = 30)
    private String transactionStatus;

    @Column(name = "decline_reason", length = 255)
    private String declineReason;

    @Column(name = "created_at")
    private LocalDateTime createdAt;




}
