package com.fraud.risk_score_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureDTO {



    // BASIC
    private Long customerId;
    private Long transactionId;


    // 1. MONEY BEHAVIOR
    private Double currentAmount;
    private Double avgDailySpend;
    private Double maxPreviousAmount;
    private Double suddenSpendIncrease;
    private Double monthlyTotalSpend;


    // 2. SPEED / FREQUENCY
    private Double txPerHour;
    private Double txPerDay;
    private Double burstScore;
    private Double timeSinceLastTx;


    // 3. DEVICE BEHAVIOR
    private Boolean newDeviceFlag;
    private Boolean trustedDevice;
    private Integer deviceChangeCount;
    private Boolean emulatorDetected;


    // 4. LOCATION BEHAVIOR
    private Boolean newCountryFlag;
    private Boolean newCityFlag;
    private Double distanceFromLastTx;
    private Boolean highRiskLocation;


    // 5. LOGIN & SECURITY
    private Integer failedLoginCount;
    private Integer otpFailureCount;
    private Boolean suspiciousIpFlag;
    private Double loginPaymentGap;


    // 6. FRAUD HISTORY
    private Integer chargebackCount;
    private Integer blockedTxCount;
    private Integer alertCount;
    private Double avgPastRiskScore;


    // 7. MERCHANT RISK
    private Double merchantFraudRate;
    private String merchantRiskCategory;
    private Boolean highRiskMerchant;


    // 8. HABIT / PROFILE
    private Double normalSpendLevel;
    private Double normalTxFrequency;
    private String normalCountry;
    private String normalChannel;
    private Double behaviorStabilityScore;


    // EXTRA (YOUR EXISTING FEATURES)
    private Double spendVelocity;
    private Double declineRatio;

    private Double ecomRatio;
    private Double posRatio;
    private Double atmRatio;

    private Double nightTransactionRatio;

}
