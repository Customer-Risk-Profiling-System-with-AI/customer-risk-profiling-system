package com.fraud.transaction_service.controller;

import com.fraud.transaction_service.dto.FeatureDTO;
import com.fraud.transaction_service.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/features")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping("/{customerId}")
    public FeatureDTO getFeatures(@PathVariable Long customerId) {
        return featureService.buildFeature(customerId);
    }
}