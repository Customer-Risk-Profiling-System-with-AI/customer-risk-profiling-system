package com.fraud.transaction_service.dto;

<<<<<<< HEAD
import com.fraud.transaction_service.entity.Channel;
import com.fraud.transaction_service.entity.TransactionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private Long transactionId;
    private Long customerId;
    private Double amount;
    private String currency;
    private Channel channel;

    private String merchantCode;
    private String deviceCode;
    private String location;

    private TransactionStatus transactionStatus;
    private LocalDateTime transactionDatetime;
    private LocalDateTime createdAt;

=======
public class TransactionResponse {
>>>>>>> upstream/backend-dev
}
