package com.fraud.transaction_service.util;


import com.fraud.transaction_service.entity.CustomerAggDaily;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;

public class FeatureCalculator {

    //Sum functions
    public static long sumLong(List<CustomerAggDaily> list, Function<CustomerAggDaily, Long> getter){

        return list.stream()
                .mapToLong(getter::apply)
                .sum();
    }

    public static BigDecimal sumAmount(List<CustomerAggDaily> list,
                                       Function<CustomerAggDaily, BigDecimal> getter) {
        return list.stream()
                .map(getter)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal maxAmount(List<CustomerAggDaily> list) {
        return list.stream()
                .map(CustomerAggDaily::getMaxTxnAmount)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    //Ratio
    public static double ratio(long numerator, long denominator) {
        if (denominator == 0) return 0.0;
        return (double) numerator / denominator;
    }

    //Velocity

    public static double velocity(BigDecimal current, BigDecimal previous) {
        if (previous.compareTo(BigDecimal.ZERO) == 0) return 0.0;

        return current.divide(previous, 4, RoundingMode.HALF_UP)
                .doubleValue();
    }

    //STD DEV
    public static BigDecimal stdDev(List<CustomerAggDaily> list,
                                    BigDecimal mean) {

        if (list.isEmpty()) return BigDecimal.ZERO;

        BigDecimal variance = list.stream()
                .map(d -> d.getAvgTxnAmount().subtract(mean))
                .map(diff -> diff.pow(2))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(list.size()), 4, RoundingMode.HALF_UP);

        return BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));
    }




}
