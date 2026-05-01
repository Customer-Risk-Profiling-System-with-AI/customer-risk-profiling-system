package com.fraud.risk_score_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDTO {

    private Long txCount30d;
    private BigDecimal totalAmount30d;
    private BigDecimal avgTxnAmount30d;
    private BigDecimal maxTxnAmount30d;
    private BigDecimal amountStddev30d;

    private Double spendVelocity7dVsPrev7d;
    private Double txVelocity7dVsPrev7d;

    private Double ecomRatio30d;
    private Double posRatio30d;
    private Double atmRatio30d;

    private Long uniqueMerchantCount30d;
    private Long riskyMerchantCount30d;

    private Boolean newDeviceFlagRecent;
    private Boolean newCountryFlagRecent;

}
