package com.fraud.alert_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertAssignRequest {

    @NotNull(message = "Analyst ID is required")
    private Long analystId;
}