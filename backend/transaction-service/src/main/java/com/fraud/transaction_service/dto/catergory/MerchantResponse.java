package com.fraud.transaction_service.dto.catergory;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantResponse {

    private String merchantCode;
    private String merchantName;
    private String merchantCategoryCode;
    private String country;
    private String riskCode;
    private LocalDateTime createdAt;
}