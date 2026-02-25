package com.fraud.transaction_service.repository;

import com.fraud.transaction_service.dto.WeeklyAggregationDTO;
import com.fraud.transaction_service.entity.CustomerAggDaily;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface CustomerAggDailyRepository extends JpaRepository<CustomerAggDaily,Long> {

    //here we dont need to create a query. it automatically creates it. because customerId and aggDate are in entity.
    Optional<CustomerAggDaily> findByCustomerIdAndAggDate(Long customerId, LocalDate aggDate);

    //Spring does NOT know: ❌ What is "DateRange"? So it cannot auto-generate SQL. You could write.
    @Query("""
    SELECT d FROM CustomerAggDaily d
    WHERE d.aggDate >= :start AND d.aggDate < :end
""")
    List<CustomerAggDaily> findAllByDateRange(@org.springframework.data.repository.query.Param("start") LocalDate start,
                                              @org.springframework.data.repository.query.Param("end") LocalDate end);

    @Query("""
        SELECT
          d.customerId,
          SUM(d.totalAmount),
          SUM(d.txCount),
          SUM(d.declineCount),
          SUM(d.ecomCount),
          SUM(d.posCount),
          SUM(d.atmCount),
          SUM(d.nightCount),
          SUM(d.hourlyPeakCount),
          MAX(d.maxTxnAmount),
          AVG(d.avgTxnAmount),
          AVG(d.stdTxnAmount),
          MAX(d.uniqueDeviceCount),
          MAX(d.uniqueCountryCount),
          MAX(d.uniqueIpCount),
          MAX(d.uniqueMerchantCount),
          SUM(d.riskyMerchantCount)
        FROM CustomerAggDaily d
        WHERE d.aggDate >= :start AND d.aggDate < :end
        GROUP BY d.customerId
    """)
    List<Object[]> rollupWeekly(LocalDate start, LocalDate end);

    interface DailyAggRow {
        Long getCustomerId();
        BigDecimal getTotalAmount();
        Long getTxCount();
        Long getDeclineCount();
        Long getEcomCount();
        Long getPosCount();
        Long getAtmCount();
        Long getNightCount();
        Long getHourlyPeakCount();
        BigDecimal getMaxTxnAmount();
        BigDecimal getAvgTxnAmount();
        BigDecimal getStdTxnAmount();
        Long getUniqueDeviceCount();
        Long getUniqueCountryCount();
        Long getUniqueIpCount();
        Long getUniqueMerchantCount();
        Long getRiskyMerchantCount();
    }

    @Query(value = """
        SELECT
          t.customer_id AS customerId,
          SUM(t.amount) AS totalAmount,
          COUNT(*) AS txCount,
          SUM(CASE WHEN t.transaction_status = 'DECLINED' THEN 1 ELSE 0 END) AS declineCount,
          SUM(CASE WHEN t.channel = 'ECOM' THEN 1 ELSE 0 END) AS ecomCount,
          SUM(CASE WHEN t.channel = 'POS'  THEN 1 ELSE 0 END) AS posCount,
          SUM(CASE WHEN t.channel = 'ATM'  THEN 1 ELSE 0 END) AS atmCount,
          SUM(CASE
                WHEN HOUR(t.transaction_datetime) >= 22 OR HOUR(t.transaction_datetime) < 5
                THEN 1 ELSE 0 END) AS nightCount,
          (
            SELECT MAX(x.cnt)
            FROM (
              SELECT COUNT(*) AS cnt
              FROM transactions t2
              WHERE t2.customer_id = t.customer_id
                AND t2.transaction_datetime >= :start
                AND t2.transaction_datetime <  :end
              GROUP BY HOUR(t2.transaction_datetime)
            ) x
          ) AS hourlyPeakCount,
          MAX(t.amount) AS maxTxnAmount,
          AVG(t.amount) AS avgTxnAmount,
          STDDEV_POP(t.amount) AS stdTxnAmount,
          COUNT(DISTINCT t.device_code)   AS uniqueDeviceCount,
          COUNT(DISTINCT t.location)      AS uniqueCountryCount,
          0                               AS uniqueIpCount,
          COUNT(DISTINCT t.merchant_code) AS uniqueMerchantCount,
          0                               AS riskyMerchantCount
        FROM transactions t
        WHERE t.transaction_datetime >= :start
          AND t.transaction_datetime <  :end
        GROUP BY t.customer_id
        """, nativeQuery = true)
    List<DailyAggRow> aggregateDaily(@Param("start") LocalDateTime start,
                                     @Param("end") LocalDateTime end);

}
