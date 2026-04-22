package com.fraud.risk_score_service.service;

import com.fraud.risk_score_service.dto.RiskRequestDTO;
import com.fraud.risk_score_service.dto.RiskResponseDTO;

import java.util.List;

public interface RiskScoreService {

    RiskResponseDTO calculateNow(Long customerId, String trigger);
    RiskResponseDTO getCurrent(Long customerId);
    List<RiskResponseDTO> getHistory(Long customerId);

}
