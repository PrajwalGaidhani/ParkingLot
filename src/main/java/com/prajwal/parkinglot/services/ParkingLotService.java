package com.prajwal.parkinglot.services;

import com.prajwal.parkinglot.exception.InvalidParkingLotException;
import com.prajwal.parkinglot.models.ParkingFloor;
import com.prajwal.parkinglot.models.VehicleType;

import java.util.List;
import java.util.Map;

public interface ParkingLotService {
    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloors, List<VehicleType> vehicleTypes) throws InvalidParkingLotException, InvalidParkingLotException;
}