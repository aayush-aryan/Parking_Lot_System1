package com.blabz.parking_lot;

import java.util.List;

public class ParkingLotAttendant {
    private List<ParkingLotOwner> parkingLots;
    private  Object vehicle;

    public ParkingLotAttendant(Object vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingLotAttendant(List parkingLots, Object vehicle) {
        this.parkingLots = parkingLots;
        this.vehicle = vehicle;
    }

    public Object getVehicle() {
        return vehicle;
    }
}
