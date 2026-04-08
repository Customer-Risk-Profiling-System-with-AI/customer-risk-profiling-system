package com.fraud.transaction_service.dto.catergory;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceRequest {

    private String deviceCode;
    private String deviceFingerprint;
    private String deviceType;
    private String operatingSystem;
    private Boolean trustedFlag;
}