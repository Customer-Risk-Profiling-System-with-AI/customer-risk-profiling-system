package com.fraud.transaction_service.dto;

import java.math.BigDecimal;

public interface DailyAggregationDTO {

    Long getCustomerId();

    BigDecimal getTotalAmount();
    Long getTxCount();
    Long getDeclineCount();

    Long getEcomCount();
    Long getPosCount();
    Long getAtmCount();

    Long getNightCount();
    Long getHourlyPeakCount();

    BigDecimal getMaxTxnAmount();
    BigDecimal getAvgTxnAmount();
    BigDecimal getStdTxnAmount();

    Long getUniqueDeviceCount();
    Long getUniqueCountryCount();
    Long getUniqueIpCount();

    Long getUniqueMerchantCount();
    Long getRiskyMerchantCount();
}