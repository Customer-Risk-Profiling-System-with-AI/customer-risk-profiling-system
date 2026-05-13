package com.fraud.transaction_service.entity.catergory_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @Column(name = "location_code")
    private String locationCode;

    @Column(nullable = false)
    private String country;
    private String region;

    @Column(nullable = false)
    private String city;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    @Column(nullable = false)
    private Double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(nullable = false)
    private Double longitude;

    private String riskFlag;
}