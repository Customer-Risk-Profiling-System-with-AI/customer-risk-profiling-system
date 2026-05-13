package com.fraud.transaction_service.dto.catergory;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceResponse {

    private String deviceCode;
    private String deviceFingerprint;
    private String deviceType;
    private String operatingSystem;
    private LocalDate firstSeenDate;
    private LocalDate lastSeenDate;
    private Boolean trustedFlag;
}