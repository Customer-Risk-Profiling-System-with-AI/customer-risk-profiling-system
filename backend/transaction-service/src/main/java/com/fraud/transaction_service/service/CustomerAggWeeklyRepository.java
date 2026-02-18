package com.fraud.transaction_service.service;

import com.fraud.transaction_service.entity.CustomerAggWeekly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerAggWeeklyRepository extends JpaRepository<CustomerAggWeekly, Long> {

    Optional<CustomerAggWeekly> findByCustomerIdAndWeekStart(Long customerId, LocalDate weekStart);
}