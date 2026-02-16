package com.fraud.risk_score_service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "risk_scores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RiskScore {
}
`