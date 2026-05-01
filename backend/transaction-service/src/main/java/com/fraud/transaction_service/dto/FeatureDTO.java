package com.fraud.transaction_service.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureDTO {

    /*private Long customerId;

    // Volume & Amount
    private Long txCount30d;
    private BigDecimal totalAmount30d;
    private BigDecimal avgTxnAmount30d;
    private BigDecimal maxTxnAmount30d;
    private BigDecimal amountStddev30d;

    // Velocity
    private Double spendVelocity7dVsPrev7d;
    private Double txVelocity7dVsPrev7d;

    // Declines
    private Double declineRatio30d;
    private Double declineTrend7dVsPrev7d;

    // Channel Mix
    private Double ecomRatio30d;
    private Double posRatio30d;
    private Double atmRatio30d;

    // Merchant / MCC
    private Long uniqueMerchantCount30d;
    private Long riskyMerchantCount30d;
    private Long uniqueMccCount30d;
    private Double highRiskMccRatio30d;

    // Behavioral Flags
    private Boolean newDeviceFlagRecent;
    private Boolean newCountryFlagRecent;*/

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