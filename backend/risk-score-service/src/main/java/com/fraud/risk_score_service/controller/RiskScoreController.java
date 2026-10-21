package com.fraud.risk_score_service.controller;

import com.fraud.risk_score_service.service.RiskScoreServiceImpl;
import com.fraud.risk_score_service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.fraud.risk_score_service.entity.RiskScore;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping({"/api/risk", "/risk"})
@RestController
public class RiskScoreController {

    private final RiskScoreServiceImpl riskScoreService;

    @GetMapping
    public List<RiskScore> getAll() {
        return riskScoreService.getAll();
    }

    @GetMapping("/calculate/{customerId}")
    public RiskResponseDTO create(@PathVariable("customerId") Long customerId, @RequestParam(defaultValue = "TRANSACTION") String trigger){
        return riskScoreService.calculateNow(customerId, trigger);
    }

    @GetMapping("/{customerId}")
    public RiskResponseDTO getCurrent(@PathVariable("customerId") Long customerId){
        return riskScoreService.getCurrent(customerId);
    }

    @GetMapping("/{customerId}/history")
    public List<RiskResponseDTO> getHistory(@PathVariable("customerId") Long customerId) {
        return riskScoreService.getHistory(customerId);
    }

}
