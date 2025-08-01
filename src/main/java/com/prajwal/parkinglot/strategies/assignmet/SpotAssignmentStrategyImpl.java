package com.prajwal.parkinglot.strategies.assignmet;

import java.util.Optional;

public class SpotAssignmentStrategyImpl implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        for (ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
            if(parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)) {
                for (ParkingSpot parkingSpot : parkingFloor.getSpots()) {
                    if (parkingSpot.getStatus().equals(ParkingSpotStatus.AVAILABLE)) {
                        if (parkingSpot.getSupportedVehicleType().equals(vehicleType)) {
                            parkingSpot.setStatus(ParkingSpotStatus.OCCUPIED);
                            return Optional.of(parkingSpot);
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }
}