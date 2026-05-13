package com.fraud.alert_service.dto;

import com.fraud.alert_service.AlertStatus.AlertSeverity;
import com.fraud.alert_service.AlertStatus.AlertType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Risk score ID is required")
    private Long riskScoreId;

    @NotNull(message = "Alert type is required")
    private AlertType alertType;

    @NotNull(message = "Alert severity is required")
    private AlertSeverity severity;

    @NotBlank(message = "Alert message is required")
    private String alertMessage;
}
