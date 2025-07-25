package com.prajwal.parkinglot.strategies.assignmet;

import com.prajwal.parkinglot.models.ParkingSpot;

import java.util.Optional;

public interface SpotAssignmentStrategy {

    Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType);

}