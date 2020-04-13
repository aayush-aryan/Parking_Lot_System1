package com.blabz.parking_lot;

public class ParkingLotException extends Exception{
    public enum ExceptionType {
        VEHICLE_ALREADY_PARKED, NO_SUCH_VEHICLE_PARKED, PARKING_CAPACITY_FULL;
    }
    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
