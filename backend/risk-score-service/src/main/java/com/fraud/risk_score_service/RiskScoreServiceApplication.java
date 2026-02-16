package com.fraud.risk_score_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RiskScoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskScoreServiceApplication.class, args);
		System.out.print("Risk_score is running");
	}

}
