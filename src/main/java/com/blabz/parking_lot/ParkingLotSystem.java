package com.blabz.parking_lot;

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem(){
    }
    public boolean park(Object vehicle) {
        if(this.vehicle!=null){
            return false;
        }
        this.vehicle=vehicle;
        return true;
    }


}
