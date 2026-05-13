package com.fraud.alert_service.dto;

import com.fraud.alert_service.AlertStatus.AlertStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertStatusUpdateRequest {

    @NotNull(message = "Status is required")
    private AlertStatus status;
}
