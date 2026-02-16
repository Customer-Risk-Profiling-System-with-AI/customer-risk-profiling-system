package com.fraud.risk_score_service.client;

import com.fraud.risk_score_service.dto.FeatureDTO;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "transaction-service", url ="${clients.transaction-service}")
public interface TransactionServiceClient {

    @GetMapping("/features/{customerId}")
    FeatureDTO getFeatures(@PathVariable("customerId") Long customerId);
}
