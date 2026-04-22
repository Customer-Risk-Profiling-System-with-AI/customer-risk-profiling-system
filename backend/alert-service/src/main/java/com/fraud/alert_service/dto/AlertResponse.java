package com.fraud.alert_service.dto;

import com.fraud.alert_service.AlertStatus.AlertSeverity;
import com.fraud.alert_service.AlertStatus.AlertStatus;
import com.fraud.alert_service.AlertStatus.AlertType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertResponse {

    private Long alertId;
    private Long customerId;
    private Long riskScoreId;
    private AlertType alertType;
    private AlertSeverity severity;
    private AlertStatus status;
    private String alertMessage;
    private Long analystId;
    private LocalDateTime triggeredAt;
    private LocalDateTime updatedAt;
}
