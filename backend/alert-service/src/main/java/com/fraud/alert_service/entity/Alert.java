package com.fraud.alert_service.entity;

import com.fraud.alert_service.AlertStatus.AlertSeverity;
import com.fraud.alert_service.AlertStatus.AlertStatus;
import com.fraud.alert_service.AlertStatus.AlertType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long riskScoreId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType alertType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertSeverity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertStatus status;

    @Column(nullable = false)
    private String alertMessage;

    private Long analystId;

    @Column(nullable = false)
    private LocalDateTime triggeredAt;

    private LocalDateTime updatedAt;
}