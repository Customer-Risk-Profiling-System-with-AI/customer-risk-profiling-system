package com.fraud.risk_score_service.repository;

import com.fraud.risk_score_service.entity.RiskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskHistoryRepository extends JpaRepository<RiskHistory,Long> {


}
