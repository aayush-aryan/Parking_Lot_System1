package com.blabz.parking_lot;

public class ParkingLotOwner implements ParKingLotObserver {
    private Boolean parkingLotIsFull;
    private Boolean parkingLotIsEmpty;
    private int count = 1;
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
    public int getParkingSlot() {
        return count++;
    }
}
