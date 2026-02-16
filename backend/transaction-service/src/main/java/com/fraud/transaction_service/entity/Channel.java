package com.fraud.transaction_service.entity;

public enum Channel {

    POS,        // Shop payments
    ATM,        // ATM
    ECOM,       // Online shopping
    MOBILE,     // Mobile apps
    QR,         // QR payments
    TRANSFER,   // Bank transfers
    BRANCH,     // Bank counter
    AUTO_DEBIT, // Subscriptions
    BILL_PAY,   // Bill payments
    CDM         // Cash deposit machine
}