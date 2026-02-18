package com.fraud.risk_score_service.controller;

import com.fraud.risk_score_service.service.RiskScoreServiceImpl;
import com.fraud.risk_score_service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/risk")
@RestController
public class RiskScoreController {

    private final RiskScoreServiceImpl riskScoreService;

    @PostMapping("/calculate/{customerId}")
    public RiskResponseDTO create(@PathVariable Long customerId, @RequestParam(defaultValue = "TRANSACTION") String trigger){
        return riskScoreService.calculateNow(customerId, trigger);
    }

    @GetMapping("/{customerId}")//Take this value from the URL and put it into my method parameter
    public RiskResponseDTO getCurrent(@Valid Long customerId){
        return riskScoreService.getCurrent(customerId);
    }

    @GetMapping("/{customerId}/history")
    public List<RiskResponseDTO> getHistory(@PathVariable Long customerId) {
        return riskScoreService.getHistory(customerId);
    }

}
