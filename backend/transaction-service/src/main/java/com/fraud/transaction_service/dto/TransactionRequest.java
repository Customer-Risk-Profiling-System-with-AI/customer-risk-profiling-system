package com.fraud.transaction_service.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class TransactionRequest {
    private Long customerId;
    private Double amount;
    private String currency;
    private String channel;

    private String merchantCode;
    private String deviceCode;
    private String location;

    private String transactionStatus;
    private String declineReason;


}
