package com.fraud.transaction_service.service;

import com.fraud.transaction_service.entity.CustomerAggDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerAggDailyRepository extends JpaRepository<CustomerAggDaily, Long> {

    Optional<CustomerAggDaily> findByCustomerIdAndAggDate(Long customerId, LocalDate aggDate);

    @Query(""" 
        SELECT d FROM CustomerAggDaily d
        WHERE d.aggDate >= :start AND d.aggDate < :end
    """) //d is like nick for CustomerAggDaily
    List<CustomerAggDaily> findAllByDateRange(LocalDate start, LocalDate end);

    /**
     * Weekly rollup from daily aggregates.
     * We return Object[] to keep it simple; you can use projection if you prefer.
     */
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
}