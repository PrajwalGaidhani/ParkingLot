package com.prajwal.parkinglot.exception;

public class ParkingSpotNotAvailableException extends Exception{
    public ParkingSpotNotAvailableException(String message) {
        super(message);
    }
}