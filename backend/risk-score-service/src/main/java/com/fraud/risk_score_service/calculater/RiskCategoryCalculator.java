package com.fraud.risk_score_service.calculater;

import org.springframework.stereotype.Component;

@Component
public class RiskCategoryCalculator {

    public String categoryFromScore(int score){

        if(score>=70) return "High";
        if(score>=40) return "Medium";
        return "Low";
    }
}
