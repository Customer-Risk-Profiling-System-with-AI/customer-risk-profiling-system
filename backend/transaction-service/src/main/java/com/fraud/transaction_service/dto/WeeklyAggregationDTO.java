package com.fraud.transaction_service.dto;

import java.math.BigDecimal;

public record WeeklyAggregationDTO(
        Long customerId,
        BigDecimal totalAmount,
        Long txCount,
        Long declineCount,
        Long ecomCount,
        Long posCount,
        Long atmCount,
        Long nightCount,
        Long hourlyPeakCount,
        BigDecimal maxTxnAmount,
        Double avgTxnAmount,   // IMPORTANT: AVG() => Double
        Double stdTxnAmount,   // IMPORTANT: AVG() => Double
        Long uniqueDeviceCount,
        Long uniqueCountryCount,
        Long uniqueIpCount,
        Long uniqueMerchantCount,
        Long riskyMerchantCount
) {}