package com.fraud.risk_score_service.client;

import com.fraud.risk_score_service.dto.FeatureDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="python-ml-service", url = "${clients.ml-service}")
public interface MlServiceClient {

    @PostMapping("/predict")
    MlPredictResponse predict(@RequestBody FeatureDTO features);

    record MlPredictResponse(
            @JsonProperty("risk_score") Double riskScore,
            @JsonProperty("risk_level") String riskLevel,
            String error
    ) {}

}
