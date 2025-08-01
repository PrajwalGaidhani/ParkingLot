package com.prajwal.parkinglot.strategies.pricing;

import java.util.Date;

public class WeekdayPricingStrategy implements PricingStrategy{

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        int hours = DateTimeUtils.calculateHours(entryTime, exitTime);
        return hours * 10;
    }
}