package com.blabz.parking_lot;
import com.blabz.parking_lot.ParkingLotException;
import java.util.List;

public class NearestFreeSpaceStrategy implements parkinglot.parkingstrategy.ParkingStrategy {

    @Override
    public ParkingLots getParkingLot(List<ParkingLots> parkingLotsList) throws ParkingLotException {
        return parkingLotsList.stream()
                .filter(lot -> lot.getNumOfVehicleParked() != lot.getCapacity())
                .findFirst()
                .orElseThrow(() -> new ParkingLotException("No Any Lots Available",
                        ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL));
    }
}
