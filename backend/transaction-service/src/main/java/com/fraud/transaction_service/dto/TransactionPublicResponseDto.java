package com.fraud.transaction_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionPublicResponseDto {

    private Long transactionId;

    private Double amount;

    private String currency;

    private String channel;

    private String transactionStatus;
}