package com.fraud.risk_score_service.service;

import com.fraud.risk_score_service.calculater.RiskCategoryCalculator;
import com.fraud.risk_score_service.calculater.RiskChangeDetector;
import com.fraud.risk_score_service.client.MlServiceClient;
import com.fraud.risk_score_service.client.TransactionServiceClient;
import com.fraud.risk_score_service.repository.*;
import com.fraud.risk_score_service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Service
public abstract class RiskScoreServiceImpl implements RiskScoreService {

    private final TransactionServiceClient transactionServiceClient;
    private final MlServiceClient mlServiceClient;

    private final RiskScoreRepository riskScoreRepository;
    private final RiskHistoryRepository riskHistoryRepository;

    private final RiskCategoryCalculator riskCategoryCalculator;
    private final RiskChangeDetector riskChangeDetector;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public RiskResponseDTO calculateNow(Long customerId, String trigger){

        return null;
    }



}
