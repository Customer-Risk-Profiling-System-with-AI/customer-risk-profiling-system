package com.fraud.transaction_service.service;

import com.fraud.transaction_service.dto.DailyAggregationDTO;
import com.fraud.transaction_service.dto.WeeklyAggregationDTO;
import com.fraud.transaction_service.entity.CustomerAggDaily;
import com.fraud.transaction_service.repository.CustomerAggDailyRepository;
import com.fraud.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyAggregationService {

    private final CustomerAggDailyRepository customerAggDailyRepository;
    private final TransactionRepository transactionRepository;

    @Transactional //Run this whole method as one safe database operation.
    public void aggregateDay(LocalDate day) {
        LocalDateTime start = day.atStartOfDay();
        LocalDateTime end = day.plusDays(1).atStartOfDay();

        List<DailyAggregationDTO> rows = transactionRepository.aggregateDaily(start, end);

        List<CustomerAggDaily> toSave = new ArrayList<>(rows.size());

        for (DailyAggregationDTO r : rows) {
            CustomerAggDaily ctm = customerAggDailyRepository
                    .findByCustomerIdAndAggDate(r.getCustomerId(), day)
                    .orElseGet(() -> CustomerAggDaily.builder()
                            .customerId(r.getCustomerId())
                            .aggDate(day)
                            .build());

            ctm.setTotalAmount(nvl(r.getTotalAmount()));
            ctm.setTxCount(nvl(r.getTxCount()));
            ctm.setDeclineCount(nvl(r.getDeclineCount()));

            ctm.setEcomCount(nvl(r.getEcomCount()));
            ctm.setPosCount(nvl(r.getPosCount()));
            ctm.setAtmCount(nvl(r.getAtmCount()));

            ctm.setNightCount(nvl(r.getNightCount()));
            ctm.setHourlyPeakCount(nvl(r.getHourlyPeakCount()));

            ctm.setMaxTxnAmount(nvl(r.getMaxTxnAmount()));
            ctm.setAvgTxnAmount(nvl(r.getAvgTxnAmount()));
            ctm.setStdTxnAmount(nvl(r.getStdTxnAmount()));

            ctm.setUniqueDeviceCount(nvl(r.getUniqueDeviceCount()));
            ctm.setUniqueCountryCount(nvl(r.getUniqueCountryCount()));
            ctm.setUniqueIpCount(nvl(r.getUniqueIpCount()));

            ctm.setUniqueMerchantCount(nvl(r.getUniqueMerchantCount()));
            ctm.setRiskyMerchantCount(nvl(r.getRiskyMerchantCount()));

            toSave.add(ctm);
        }

        customerAggDailyRepository.saveAll(toSave);
    }

    public Optional<CustomerAggDaily> getByCustomerAndDate(Long customerId, LocalDate day) {
        return customerAggDailyRepository.findByCustomerIdAndAggDate(customerId, day);
    }

    public List<CustomerAggDaily> getByDateRange(LocalDate start, LocalDate end) {
        return customerAggDailyRepository.findAllByDateRange(start, end);
    }

    public List<WeeklyAggregationDTO> rollup(LocalDate start, LocalDate end) {
        return customerAggDailyRepository.rollupRange(start.atStartOfDay(), end.atStartOfDay());
    }

    private static BigDecimal nvl(BigDecimal v) { return v == null ? BigDecimal.ZERO : v; }
    private static Long nvl(Long v) { return v == null ? 0L : v; }
}
