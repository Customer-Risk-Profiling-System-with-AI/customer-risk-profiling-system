package com.fraud.transaction_service.repository;

import com.fraud.transaction_service.entity.CustomerAggDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@Repository
public interface CustomerAggDailyRepository extends JpaRepository<CustomerAggDaily,Long> {

    //here we dont need to create a query. it automatically creates it. because customerId and aggDate are in entity.
    Optional<CustomerAggDaily> findByCustomerIdAndAggDate(Long customerId, LocalDate aggDate);

    @Query("""
        SELECT d FROM CustomerAggDaily d
        WHERE d.aggDate >= :start AND d.aggDate < :end
    """) //Spring does NOT know: ❌ What is "DateRange"? So it cannot auto-generate SQL. You could write:
    List<CustomerAggDaily> findAllByDateRange(LocalDate start, LocalDate end);

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
