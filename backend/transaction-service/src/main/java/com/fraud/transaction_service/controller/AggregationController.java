package com.fraud.transaction_service.controller;

import com.fraud.transaction_service.dto.DailyAggregationDTO;
import com.fraud.transaction_service.dto.WeeklyAggregationDTO;
import com.fraud.transaction_service.entity.CustomerAggDaily;
import com.fraud.transaction_service.service.DailyAggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customer-agg-daily")
@RequiredArgsConstructor
public class AggregationController {

    private final DailyAggregationService dailyAggregationService;

    //Start calculating and saving daily summary now
    @PostMapping("/run")
    public String runAggregation(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate day
    ) {
        dailyAggregationService.aggregateDay(day);
        return "Aggregation completed for: " + day;
    }

    //Get one customer's daily aggregation
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerAggDaily> getDailyAgg(
            //Take value from URL path. /25
            @PathVariable Long customerId,

            //Take value from ?key=value part.(day = 2026-02-25)
            @RequestParam

            //Expect date like this(YYYY-MM-DD)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day)
    {
        return dailyAggregationService.getByCustomerAndDate(customerId, day)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Get all rows in a date range
    @GetMapping("/range")
    public List<CustomerAggDaily> getByRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return dailyAggregationService.getByDateRange(start, end);
    }

    //Weekly rollup (grouped by customer)
    @GetMapping("/weekly-rollup")
    public List<WeeklyAggregationDTO> weeklyRollup(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return dailyAggregationService.rollup(start, end);
    }

    /*//Store weekly aggregation rows into customer_agg_weekly table
    @PostMapping("/weekly/run")
    public String runWeekly(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate weekStart
    ) {
        // you need to inject WeeklyAggregationService in this controller
        // private final WeeklyAggregationService weeklyAggregationService;
        dailyAggregationService.aggregateWeek(weekStart);
        return "Weekly aggregation stored for week starting: " + weekStart;
    }
*/
}
