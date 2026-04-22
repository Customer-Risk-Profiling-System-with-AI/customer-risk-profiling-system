package com.fraud.risk_score_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RiskScoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskScoreServiceApplication.class, args);
<<<<<<< HEAD
		System.out.print("Risk_score is running.................................");
=======
		System.out.print("Risk_score is running");
>>>>>>> c2abe78c0175ad6f7fcac3be43c88450fe1ca36a
	}

}
