package com.fraud.transaction_service.repository;

import com.fraud.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List; //Collection (group) of items
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    //A box to hold aggregated data.
    interface DailyAggRow {
        Long getCustomerId();

        java.math.BigDecimal getTotalAmount();
        Long getTxCount();
        Long getDeclineCount();

        Long getEcomCount();
        Long getPosCount();
        Long getAtmCount();

        Long getNightCount();
        Long getHourlyPeakCount();

        java.math.BigDecimal getMaxTxnAmount();
        java.math.BigDecimal getAvgTxnAmount();
        java.math.BigDecimal getStdTxnAmount();

        Long getUniqueDeviceCount();
        Long getUniqueCountryCount();
        Long getUniqueIpCount();

        Long getUniqueMerchantCount();
        Long getRiskyMerchantCount();
    }

    @Query(value = """
        WITH base AS (
            SELECT
                t.customer_id AS customerId,
                t.amount      AS amount,
                t.status      AS status,
                t.channel     AS channel,
                t.tx_time     AS txTime,
                t.device_id   AS deviceId,
                t.country     AS country,
                t.ip_address  AS ipAddress,
                t.merchant_id AS merchantId,
                COALESCE(t.merchant_risky, FALSE) AS merchantRisky
            FROM transactions t
            WHERE t.tx_time >= :start AND t.tx_time < :end
        ),
        per_hour AS (
            SELECT
                customerId,
                DATE_TRUNC('hour', txTime) AS hr,
                COUNT(*) AS cnt
            FROM base
            GROUP BY customerId, DATE_TRUNC('hour', txTime)
        ),
        peak AS (
            SELECT customerId, COALESCE(MAX(cnt),0) AS hourlyPeakCount
            FROM per_hour
            GROUP BY customerId
        )
        SELECT
            b.customerId AS customerId,

            COALESCE(SUM(b.amount),0) AS totalAmount,
            COUNT(*) AS txCount,

            SUM(CASE WHEN b.status = 'DECLINED' THEN 1 ELSE 0 END) AS declineCount,

            SUM(CASE WHEN b.channel = 'ECOM' THEN 1 ELSE 0 END) AS ecomCount,
            SUM(CASE WHEN b.channel = 'POS'  THEN 1 ELSE 0 END) AS posCount,
            SUM(CASE WHEN b.channel = 'ATM'  THEN 1 ELSE 0 END) AS atmCount,

            SUM(CASE WHEN EXTRACT(HOUR FROM b.txTime) >= 22 OR EXTRACT(HOUR FROM b.txTime) < 5
                     THEN 1 ELSE 0 END) AS nightCount,

            COALESCE(p.hourlyPeakCount,0) AS hourlyPeakCount,

            COALESCE(MAX(b.amount),0) AS maxTxnAmount,
            COALESCE(AVG(b.amount),0) AS avgTxnAmount,
            COALESCE(STDDEV_POP(b.amount),0) AS stdTxnAmount,

            COUNT(DISTINCT b.deviceId)   AS uniqueDeviceCount,
            COUNT(DISTINCT b.country)    AS uniqueCountryCount,
            COUNT(DISTINCT b.ipAddress)  AS uniqueIpCount,

            COUNT(DISTINCT b.merchantId) AS uniqueMerchantCount,
            SUM(CASE WHEN b.merchantRisky THEN 1 ELSE 0 END) AS riskyMerchantCount

        FROM base b
        LEFT JOIN peak p ON p.customerId = b.customerId
        GROUP BY b.customerId, p.hourlyPeakCount
        """, nativeQuery = true)
    List<DailyAggRow> aggregateDaily(LocalDateTime start, LocalDateTime end);

    //📌 List = many
    //📌 Optional = zero or one
    //“Query by Method Name”
    List<Transaction> findByCustomerId(Long customerId);
    Optional<Transaction> findByTransactionId(Long transactionId);

    List<Transaction> findByTransactionDatetimeBetween(LocalDateTime start, LocalDateTime end);
    List<Transaction> findByTransactionStatus(String transactionStatus);
}