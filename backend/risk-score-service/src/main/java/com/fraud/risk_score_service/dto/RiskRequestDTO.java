package com.fraud.risk_score_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskRequestDTO {

    private Long customerId;
    private String trigger;          // TRANSACTION / DAILY_BATCH / MANUAL
    private Boolean forceRecalculate;
    private LocalDateTime requestTime;
}
