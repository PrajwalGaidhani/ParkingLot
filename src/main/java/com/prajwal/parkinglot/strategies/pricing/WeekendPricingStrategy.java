package com.prajwal.parkinglot.strategies.pricing;

import com.prajwal.parkinglot.models.Slab;

import java.util.Date;
import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy{

    private SlabRepository slabRepository;

    public WeekendPricingStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slabs = this.slabRepository.getSortedSlabsByVehicleType(vehicleType);
        int hoursSpent = DateTimeUtils.calculateHours(entryTime, exitTime);
        double amount = 0;
        for (Slab slab : slabs) {
            if(hoursSpent > slab.getEndHour() && slab.getEndHour() != -1) { // this means we have consumed entire slab
                amount += (slab.getEndHour() - slab.getStartHour()) * slab.getPrice();
            }
            else if(slab.getEndHour() == -1 || hoursSpent <= slab.getEndHour()){
                amount += (hoursSpent - slab.getStartHour()) * slab.getPrice();
                break;
            }
        }
        return amount;
    }
}
