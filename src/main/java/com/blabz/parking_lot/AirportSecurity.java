package com.blabz.parking_lot;
public class AirportSecurity {
    private Boolean parkingLotIsFull;
    private Boolean parkingLotIsEmpty;
    public Boolean isParkingLotFull() {
        return parkingLotIsFull;
    }
    public void setParkingLotCapacityFull() {
        parkingLotIsFull = true;
    }
    public void parkingLotIsEmpty() {
        parkingLotIsEmpty = true;
    }

}


