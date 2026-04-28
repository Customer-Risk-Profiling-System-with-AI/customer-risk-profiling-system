package com.fraud.transaction_service.dto.catergory;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationRequest {

    private String locationCode;
    private String country;
    private String region;
    private String city;
    private Double latitude;
    private Double longitude;
    private String riskFlag;
}