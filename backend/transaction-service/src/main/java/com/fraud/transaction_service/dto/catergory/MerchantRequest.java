package com.fraud.transaction_service.dto.catergory;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantRequest {

    private String merchantCode;
    private String merchantName;
    private String merchantCategoryCode;
    private String country;
    private String riskCode;
}