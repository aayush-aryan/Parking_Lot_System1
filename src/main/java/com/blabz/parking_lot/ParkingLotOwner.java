package com.blabz.parking_lot;

public class ParkingLotOwner implements ParKingLotObserver {
    private Boolean parkingLotIsFull;
    private Boolean parkingLotIsEmpty;
    public Boolean isParkingLotFull() {
        return parkingLotIsFull;
    }
    public Boolean isParkingLotEmpty() {
        return parkingLotIsEmpty;
    }
    public void setParkingLotCapacityFull() {
        parkingLotIsFull = true;
    }
    public void parkingLotIsEmpty() {
        parkingLotIsEmpty = true;
    }
}
