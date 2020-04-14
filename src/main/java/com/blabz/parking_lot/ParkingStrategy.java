package com.blabz.parking_lot;
import java.util.List;
public interface ParkingStrategy {
    ParkingLots getParkingLot(List<ParkingLots> listOfLots) throws ParkingLotException;
}
