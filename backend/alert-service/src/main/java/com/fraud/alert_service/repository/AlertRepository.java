package com.fraud.alert_service.repository;

import com.fraud.alert_service.AlertStatus.AlertStatus;
import com.fraud.alert_service.entity.Alert;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByCustomerId(Long customerId);

    List<Alert> findByStatus(AlertStatus status);

    List<Alert> findByAnalystId(Long analystId);
}