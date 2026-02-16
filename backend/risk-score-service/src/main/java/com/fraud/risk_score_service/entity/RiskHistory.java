package com.fraud.risk_score_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="risk_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riskHistoryId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false, length = 16)
    private String category;

    private Integer previousScore;
    private String previousCategory;

    @Column(nullable = false)
    private LocalDateTime calculatedAt;

    @Column(nullable = false, length = 32)
    private String trigger; // TRANSACTION/DAILY_BATCH/MANUAL

    private String modelVersion;

    private Boolean alertTriggered;

    @Column(columnDefinition = "TEXT")
    private String featureSnapshotJson;

    @Column(columnDefinition = "TEXT")
    private String reasonsJson;
}


