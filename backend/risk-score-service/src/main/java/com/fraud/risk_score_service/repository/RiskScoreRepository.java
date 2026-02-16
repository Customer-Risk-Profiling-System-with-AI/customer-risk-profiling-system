package com.fraud.risk_score_service.repository;

import org.springframework.stereotype.Repository;
import com.fraud.risk_score_service.entity.RiskScore;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RiskScoreRepository extends JpaRepository<RiskScore,Long> {

}
