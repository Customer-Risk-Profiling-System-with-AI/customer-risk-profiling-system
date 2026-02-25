/*
package com.fraud.transaction_service.service;

import com.fraud.transaction_service.dto.WeeklyAggregationDTO;
import com.fraud.transaction_service.entity.CustomerAggWeekly;
import com.fraud.transaction_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyAggregationService {

    private final CustomerAggDailyRepository dailyRepo;
    private final CustomerAggWeeklyRepository weeklyRepo;

    @Transactional
    public void aggregate(LocalDate weekStart) {

        LocalDate end = weekStart.plusDays(7);

        List<WeeklyAggregationDTO> rows = dailyRepo.aggregateWeekly(weekStart, end);

        for (WeeklyAggregationDTO r : rows) {

            CustomerAggWeekly w =
                    weeklyRepo.findByCustomerIdAndWeekStart(
                            r.getCustomerId(), weekStart
                    ).orElse(new CustomerAggWeekly());

            w.setCustomerId(r.getCustomerId());
            w.setWeekStart(weekStart);

            w.setTotalAmount(r.getTotalAmount());
            w.setTxCount(r.getTxCount());
            w.setDeclineCount(r.getDeclineCount());

            w.setEcomCount(r.getEcomCount());
            w.setPosCount(r.getPosCount());
            w.setAtmCount(r.getAtmCount());

            w.setNightCount(r.getNightCount());
            w.setHourlyPeakCount(r.getHourlyPeakCount());

            w.setMaxTxnAmount(r.getMaxTxnAmount());
            w.setAvgTxnAmount(r.getAvgTxnAmount());
            w.setStdTxnAmount(r.getStdTxnAmount());

            w.setUniqueDeviceCount(r.getUniqueDeviceCount());
            w.setUniqueCountryCount(r.getUniqueCountryCount());
            w.setUniqueIpCount(r.getUniqueIpCount());

            w.setUniqueMerchantCount(r.getUniqueMerchantCount());
            w.setRiskyMerchantCount(r.getRiskyMerchantCount());

            weeklyRepo.save(w);
        }
    }
}*/
