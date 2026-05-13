package com.fraud.alert_service.service;

import com.fraud.alert_service.AlertStatus.AlertStatus;
import com.fraud.alert_service.dto.*;
import com.fraud.alert_service.entity.Alert;
import com.fraud.alert_service.exception.ResourceNotFoundException;
import com.fraud.alert_service.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertResponse createAlert(AlertRequest request) {
        Alert alert = Alert.builder()
                .customerId(request.getCustomerId())
                .riskScoreId(request.getRiskScoreId())
                .alertType(request.getAlertType())
                .severity(request.getSeverity())
                .status(AlertStatus.OPEN)
                .alertMessage(request.getAlertMessage())
                .triggeredAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Alert saved = alertRepository.save(alert);
        return mapToResponse(saved);
    }

    public List<AlertResponse> getAllAlerts() {
        return alertRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AlertResponse getAlertById(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found: " + alertId));
        return mapToResponse(alert);
    }

    public List<AlertResponse> getAlertsByCustomerId(Long customerId) {
        return alertRepository.findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AlertResponse> getAlertsByStatus(AlertStatus status) {
        return alertRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AlertResponse> getAlertsByAnalystId(Long analystId) {
        return alertRepository.findByAnalystId(analystId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AlertResponse updateAlertStatus(Long alertId, AlertStatusUpdateRequest request) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found: " + alertId));

        alert.setStatus(request.getStatus());
        alert.setUpdatedAt(LocalDateTime.now());

        Alert saved = alertRepository.save(alert);
        return mapToResponse(saved);
    }

    public AlertResponse assignAnalyst(Long alertId, AlertAssignRequest request) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found: " + alertId));

        alert.setAnalystId(request.getAnalystId());
        alert.setUpdatedAt(LocalDateTime.now());

        Alert saved = alertRepository.save(alert);
        return mapToResponse(saved);
    }

    private AlertResponse mapToResponse(Alert alert) {
        return AlertResponse.builder()
                .alertId(alert.getAlertId())
                .customerId(alert.getCustomerId())
                .riskScoreId(alert.getRiskScoreId())
                .alertType(alert.getAlertType())
                .severity(alert.getSeverity())
                .status(alert.getStatus())
                .alertMessage(alert.getAlertMessage())
                .analystId(alert.getAnalystId())
                .triggeredAt(alert.getTriggeredAt())
                .updatedAt(alert.getUpdatedAt())
                .build();
    }
}