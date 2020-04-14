package com.blabz.parking_lot;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLots {
    ParkingLotOwner owner = new ParkingLotOwner();
    parkinglot.parkinglotcomponent.SlotAllotments slotAllotments;
    private int parkingLotCapacity;
    private List<ParkingLotObserver> observers;
    Map<Integer, Vehicle> vehicleSlotMap = new HashMap<>();
    Map<Vehicle, LocalDateTime> vehicleTimeTable = new HashMap<>();
    public ParkingLots(int capacity){
        this.parkingLotCapacity = capacity;
        this.observers = new ArrayList<>();
        this.slotAllotments = new parkinglot.parkinglotcomponent.SlotAllotments(capacity);
    }
    public void registeredParkingLotObserver(ParkingLotObserver observer){
        this.observers.add(observer);
    }
    public boolean park(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicleSlotMap.size() == this.parkingLotCapacity) {
            for ( ParkingLotObserver observer : observers )
                observer.setParkingLotCapacityFull();
            throw new ParkingLotException("Sorry, Parking Lot Is Full",
                    ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL);
        }
        if (this.vehicleSlotMap.containsValue(vehicle)) {
            throw new ParkingLotException("Car already present in parking lot!",
                    ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED);
        }
        LocalDateTime localDateAndTime = LocalDateTime.now();
        vehicleTimeTable.put(vehicle,localDateAndTime);
        vehicleSlotMap.put(slotAllotments.getSlot(),vehicle);
        return true;
    }
        public boolean unPark(Vehicle vehicle) throws ParkingLotException {
            if (this.vehicleSlotMap.containsValue(vehicle)) {
                slotAllotments.markSlotAsUnOccupied(getKey(vehicle));
                this.vehicleSlotMap.remove(getKey(vehicle));
                for ( ParkingLotObserver observer : observers )
                       observer.parkingLotIsEmpty();
                return true;
            }
            throw new ParkingLotException("No such car is present in parking lot",
                    ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED);
    }
     public Integer getKey(Vehicle vehicle){
        Set<Integer> keySet = vehicleSlotMap.keySet();
        Integer position=-1;
        for (Integer key : keySet) {
            if(vehicleSlotMap.get(key)==vehicle)
                position =  key;
        }
        return position;
    }
    public int getAvailability() {
       return parkingLotCapacity - vehicleSlotMap.size();
    }
    public boolean getMyVehicle(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicleSlotMap.containsValue(vehicle))
            return true;
        throw new ParkingLotException("No such car is present in parking lot",
                ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED);
    }
    public LocalDateTime getVehicleTimeTable(Vehicle vehicle) throws ParkingLotException {
        LocalDateTime localDateTime = LocalDateTime.now();
        if(getMyVehicle(vehicle))
            localDateTime = vehicleTimeTable.get(vehicle);
        return localDateTime;
    }
    public Set<Integer> getOccupiedSlotSet() {
        return  vehicleSlotMap.keySet();
    }
    public Integer getNumOfVehicleParked() {
        return vehicleSlotMap.size();
    }
    public Integer getCapacity() {
        return parkingLotCapacity;
    }
    public ParkingLots getParkingLot(List<ParkingLots> parkingLotsList,DriverType driverType) throws ParkingLotException {
        return slotAllotments.getParkingLot(parkingLotsList, driverType);
    }
}
