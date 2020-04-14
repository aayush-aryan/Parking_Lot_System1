       package com.blabz.parking_lot;

import java.util.ArrayList;
import java.util.List;

public class SlotAllotments {
    private int parkingLotCapacity;
    private List<Integer> slots = new ArrayList<>();

    public SlotAllotments(int capacity) {
        this.parkingLotCapacity = capacity;
    }
    public void markSlotAsUnOccupied(Integer position) {
        slots.remove(position);
    }
    public Integer getSlot() {
        Integer position = getSlotByEvenlyDistribution();
        slots.add(position);
        return position;
    }
    public ParkingLots getParkingLot(List<ParkingLots> parkingLotsList) throws ParkingLotException {
        ParkingStrategy parkingStrategy = (ParkingStrategy) new EvenDistributionParkingStrategy();
        return parkingStrategy.getParkingLot(parkingLotsList);
    }
    public Integer getSlotByEvenlyDistribution() {
        Integer i = 0;
        for (i = 1; i <= parkingLotCapacity; i++){
            if (!slots.contains(i))
                break;
    }
    return i;
}
}
