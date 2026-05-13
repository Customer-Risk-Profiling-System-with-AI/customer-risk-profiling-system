package com.fraud.transaction_service.repository;

import com.fraud.transaction_service.entity.CustomerAggWeekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CustomerAggWeeklyRepository extends JpaRepository<CustomerAggWeekly,Long> {
    Optional<CustomerAggWeekly> findByCustomerIdAndWeekStart(Long customerId, LocalDate weekStart);

}
