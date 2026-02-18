package com.fraud.risk_score_service.service;

import com.fraud.risk_score_service.calculater.RiskCategoryCalculator;
import com.fraud.risk_score_service.calculater.RiskChangeDetector;
import com.fraud.risk_score_service.client.MlServiceClient;
import com.fraud.risk_score_service.client.TransactionServiceClient;
import com.fraud.risk_score_service.entity.RiskHistory;
import com.fraud.risk_score_service.entity.RiskScore;
import com.fraud.risk_score_service.exception.ResourceNotFoundException;
import com.fraud.risk_score_service.repository.*;
import com.fraud.risk_score_service.dto.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public abstract class RiskScoreServiceImpl implements RiskScoreService {

    private final TransactionServiceClient transactionServiceClient;
    private final MlServiceClient mlServiceClient;

    private final RiskScoreRepository riskScoreRepository;
    private final RiskHistoryRepository riskHistoryRepository;

    private final RiskCategoryCalculator riskCategoryCalculator;
    private final RiskChangeDetector riskChangeDetector;

    private final ObjectMapper objectMapper = new ObjectMapper();//Convert Java objects → JSON

    private String toJsonSafe(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }

    private List<String> buildReasons(FeatureDTO features, int score) {
        //TODO: replace with real rules
        List<String> reasons = new ArrayList<>();

        if (score >= 70) reasons.add("High predicted fraud score");

        if (features.getDeclineRatio() > 0.4)
            reasons.add("Too many declined transactions");

        if (features.getSpendVelocity() > 3.0)
            reasons.add("Unusually fast spending");

        return reasons;
    }

    @Override
    public RiskResponseDTO calculateNow(Long customerId, String trigger){

        FeatureDTO features = transactionServiceClient.getFeatures(customerId);

        if(features == null){
            throw new ResourceNotFoundException("No features found for customer " + customerId);
        }

        //ML part. (that feature send to ml service and get score)
        MlServiceClient.MlPredictResponse ml = mlServiceClient.predict(features);
        int score = 100; //clampScore(ml.score());

        String category = riskCategoryCalculator.categoryFromScore(score);

        //Read previous score (Optional)
        Optional<RiskScore> previous = riskScoreRepository.findByCustomerId(customerId);

        /*Integer preScore = previous.map(RiskScore::getScore).orElse(null);
        String preCategory = previous.map(RiskScore::getCategory).orElse(null);

        boolean movedUp = previous
                .map(oldRisk-> riskChangeDetector.movedUp(oldRisk.getCategory(), category))
                .orElse(false);*/

        Integer preScore = null;
        String preCategory = null;
        boolean movedUp = false;

        if (previous.isPresent()) {
            RiskScore old = previous.get();

            preScore = old.getScore();
            preCategory = old.getCategory();
            movedUp = riskChangeDetector.movedUp(preCategory, category);
        }

        // Save current score (update or create)
        RiskScore current = previous.orElseGet(RiskScore::new); //If previous has a RiskScore → use it. If previous is empty → create a NEW RiskScore object.
        if (current.getCustomerId() == null) { // only for newly created
            current.setCustomerId(customerId);
        }
        current.setScore(score);
        current.setCategory(category);
        current.setUpdatedAt(LocalDateTime.now());

        riskScoreRepository.save(current);

        //Build reasons
        List<String> reasons = buildReasons(features,score);

        //save history entry
        RiskHistory history = new RiskHistory();
        history.setScore(score);
        history.setCategory(category);
        history.setPreviousScore(preScore);
        history.setPreviousCategory(preCategory);
        history.setCalculatedAt(LocalDateTime.now());

        history.setTrigger(trigger == null ? "TRANSACTION" : trigger);
        history.setAlertTriggered(movedUp);
        history.setFeatureSnapshotJson(toJsonSafe(features));
        history.setReasonsJson(toJsonSafe(reasons));

        riskHistoryRepository.save(history);

        RiskResponseDTO response = new RiskResponseDTO();
        response.setCustomerId(customerId);
        response.setScore(score);
        response.setCategory(category);
        response.setCalculatedAt(history.getCalculatedAt());
        response.setReasons(reasons);

        return response;

    }



}
