package com.blabz.parking_lot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {
    private List parkedVehicles;
    private List<ParkingLotObserver> observers;
    private int parkingLotCapacity;
    Map<Integer,Object> vehicleSlotMap = new HashMap<>();

    public ParkingLotSystem(int capacity) {
        this.parkingLotCapacity = capacity;
        this.parkedVehicles = new ArrayList();
        this.observers = new ArrayList<>();
    }
    public void registeredParkingLotObserver(ParkingLotObserver observer){
        this.observers.add(observer);
    }
    public void park(Object vehicle ) throws ParkingLotException {
        if (this.parkedVehicles.size() == this.parkingLotCapacity) {
            for ( ParkingLotObserver observer : observers )
                observer.setParkingLotCapacityFull();
            throw new ParkingLotException("Sorry, Parking Lot Is Full",
                    ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL);
        }
        if (this.isThisVehiclePresentInParkingLot(vehicle)) {
            throw new ParkingLotException("Car already present in parking lot!",
                    ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED);
        }
        this.parkedVehicles.add(vehicle);
    }
    private void park(int slot, Object vehicle) throws ParkingLotException {
        if (this.vehicleSlotMap.size() == this.parkingLotCapacity) {
            for ( ParkingLotObserver observer : observers )
                observer.setParkingLotCapacityFull();
            throw new ParkingLotException("Sorry, Parking Lot Is Full",
                    ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL);
        }
        vehicleSlotMap.put(slot,vehicle);
    }
    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.isThisVehiclePresentInParkingLot(vehicle)) {
            this.parkedVehicles.remove(vehicle);
            for ( ParkingLotObserver observer : observers )
                observer.parkingLotIsEmpty();
            return;
        }
        throw new ParkingLotException("No such car is present in parking lot",
                ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED);
    }
    public boolean isThisVehiclePresentInParkingLot(Object vehicle) {
        if (this.parkedVehicles.contains(vehicle) || this.vehicleSlotMap.containsValue(vehicle))
            return true;
        return false;
    }
    public ParkingLotAttendant getParkingLotAttendant(ParkingLotAttendant attendantWithVehicle) throws ParkingLotException {
        ParkingLotOwner parkingLotOwner = (ParkingLotOwner) observers.get(0);
        park(parkingLotOwner.getParkingSlot(),attendantWithVehicle.getVehicle());
        return attendantWithVehicle;
    }
    public ParkingLotAttendant getMyVehicle(ParkingLotAttendant parkingLotAttendant) throws ParkingLotException {
        if( vehicleSlotMap.containsValue(parkingLotAttendant.getVehicle()))
            return parkingLotAttendant;
        throw new ParkingLotException("No such car is present in parking lot",
                ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED);
    }
}
