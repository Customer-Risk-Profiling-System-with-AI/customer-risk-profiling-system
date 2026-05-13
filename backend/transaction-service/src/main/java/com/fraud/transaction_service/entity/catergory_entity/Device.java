package com.fraud.transaction_service.entity.catergory_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "device")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    @Id
    @Column(name = "device_code")
    private String deviceCode;

    @Column(nullable = false, unique = true)
    private String deviceFingerprint;
    private String deviceType;
    private String operatingSystem;
    private LocalDate firstSeenDate;
    private LocalDate lastSeenDate;
    private Boolean trustedFlag;

}
