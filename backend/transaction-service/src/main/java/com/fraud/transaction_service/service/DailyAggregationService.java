package com.fraud.transaction_service.service;

import com.fraud.transaction_service.dto.DailyAggregationDTO;
import com.fraud.transaction_service.entity.CustomerAggDaily;
import com.fraud.transaction_service.repository.CustomerAggDailyRepository;
import com.fraud.transaction_service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyAggregationService {

    private final CustomerAggDailyRepository customerAggDailyRepository;
    private final TransactionRepository transactionRepository;

    @Transactional//Run this whole method as one safe database operation.
    public void aggregateDay(LocalDate day){
        LocalDateTime start = day.atStartOfDay();
        LocalDateTime end = day.plusDays(1).atStartOfDay();

        List<DailyAggregationDTO> all = transactionRepository.aggregateDaily(start, end);

        for(var r : all){
            CustomerAggDaily ctm = customerAggDailyRepository.findByCustomerIdAndAggDate(r.getCustomerId(),day)
                    .orElse(CustomerAggDaily.builder()
                            .customerId(r.getCustomerId())
                            .aggDate(day)
                            .build());

            ctm.setTotalAmount(r.getTotalAmount());
            ctm.setTxCount(r.getTxCount());
            ctm.setDeclineCount(r.getDeclineCount());

            ctm.setEcomCount(r.getEcomCount());
            ctm.setPosCount(r.getPosCount());
            ctm.setAtmCount(r.getAtmCount());

            ctm.setNightCount(r.getNightCount());
            ctm.setHourlyPeakCount(r.getHourlyPeakCount());

            ctm.setMaxTxnAmount(r.getMaxTxnAmount());
            ctm.setAvgTxnAmount(r.getAvgTxnAmount());
            ctm.setStdTxnAmount(r.getStdTxnAmount());

            ctm.setUniqueDeviceCount(r.getUniqueDeviceCount());
            ctm.setUniqueCountryCount(r.getUniqueCountryCount());
            ctm.setUniqueIpCount(r.getUniqueIpCount());

            ctm.setUniqueMerchantCount(r.getUniqueMerchantCount());
            ctm.setRiskyMerchantCount(r.getRiskyMerchantCount());

            customerAggDailyRepository.save(ctm);

        }



    }

    public Optional<CustomerAggDaily> getByCustomerAndDate(Long customerId, LocalDate day){
        
        return customerAggDailyRepository.findByCustomerIdAndAggDate(customerId,day);
    }

    public List<CustomerAggDaily> getByDateRange(LocalDate start, LocalDate end){

        return customerAggDailyRepository.findAllByDateRange(start,end);

    }

    public List<Object[]> rollupWeekly(LocalDate start, LocalDate end){
        return customerAggDailyRepository.rollupWeekly(start, end);
    }


}
