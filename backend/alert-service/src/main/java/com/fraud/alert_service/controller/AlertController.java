package com.fraud.alert_service.controller;


import com.fraud.alert_service.AlertStatus.AlertStatus;
import com.fraud.alert_service.dto.*;
import com.fraud.alert_service.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    @PostMapping
    public AlertResponse createAlert(@Valid @RequestBody AlertRequest request) {
        return alertService.createAlert(request);
    }

    @GetMapping
    public List<AlertResponse> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/{alertId}")
    public AlertResponse getAlertById(@PathVariable Long alertId) {
        return alertService.getAlertById(alertId);
    }

    @GetMapping("/customer/{customerId}")
    public List<AlertResponse> getAlertsByCustomerId(@PathVariable Long customerId) {
        return alertService.getAlertsByCustomerId(customerId);
    }

    @GetMapping("/status/{status}")
    public List<AlertResponse> getAlertsByStatus(@PathVariable AlertStatus status) {
        return alertService.getAlertsByStatus(status);
    }

    @GetMapping("/analyst/{analystId}")
    public List<AlertResponse> getAlertsByAnalystId(@PathVariable Long analystId) {
        return alertService.getAlertsByAnalystId(analystId);
    }

    @PatchMapping("/{alertId}/status")
    public AlertResponse updateAlertStatus(
            @PathVariable Long alertId,
            @Valid @RequestBody AlertStatusUpdateRequest request
    ) {
        return alertService.updateAlertStatus(alertId, request);
    }

    @PatchMapping("/{alertId}/assign")
    public AlertResponse assignAnalyst(
            @PathVariable Long alertId,
            @Valid @RequestBody AlertAssignRequest request
    ) {
        return alertService.assignAnalyst(alertId, request);
    }
}
