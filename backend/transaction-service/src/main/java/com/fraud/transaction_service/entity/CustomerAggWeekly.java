package com.fraud.transaction_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "customer_agg_weekly",
        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id","week_start"})
)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CustomerAggWeekly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_id", nullable=false)
    private Long customerId;

    @Column(name="week_start", nullable=false)
    private LocalDate weekStart; // Monday

    // Volume
    @Column(name="total_amount", nullable=false)
    private BigDecimal totalAmount;

    @Column(name="tx_count", nullable=false)
    private Long txCount;

    // Declines
    @Column(name="decline_count", nullable=false)
    private Long declineCount;

    // Channels
    @Column(name="ecom_count", nullable=false)
    private Long ecomCount;

    @Column(name="pos_count", nullable=false)
    private Long posCount;

    @Column(name="atm_count", nullable=false)
    private Long atmCount;

    // Time
    @Column(name="night_count", nullable=false)
    private Long nightCount;

    @Column(name="hourly_peak_count", nullable=false)
    private Long hourlyPeakCount;

    // Amount risk (weekly derived)
    @Column(name="max_txn_amount", nullable=false)
    private BigDecimal maxTxnAmount;

    @Column(name="avg_txn_amount", nullable=false)
    private BigDecimal avgTxnAmount;

    @Column(name="std_txn_amount", nullable=false)
    private BigDecimal stdTxnAmount;

    // Device / Geo
    @Column(name="unique_device_count", nullable=false)
    private Long uniqueDeviceCount;

    @Column(name="unique_country_count", nullable=false)
    private Long uniqueCountryCount;

    @Column(name="unique_ip_count", nullable=false)
    private Long uniqueIpCount;

    // Merchant
    @Column(name="unique_merchant_count", nullable=false)
    private Long uniqueMerchantCount;

    @Column(name="risky_merchant_count", nullable=false)
    private Long riskyMerchantCount;
}