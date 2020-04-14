package com.blabz.parking_lot;

import java.util.Comparator;
import java.util.List;

public class EvenDistributionParkingStrategy implements parkinglot.parkingstrategy.ParkingStrategy {
    @Override
    public ParkingLots getParkingLot(List<ParkingLots> parkingLotsList) throws ParkingLotException {
        return parkingLotsList.stream()
                .sorted(Comparator.comparing(lot -> lot.getNumOfVehicleParked()))
                .filter(lot -> lot.getNumOfVehicleParked() != lot.getCapacity())
                .findFirst()
                .orElseThrow(() -> new ParkingLotException("No Any Lots Available",
                        ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL));
    }
}
