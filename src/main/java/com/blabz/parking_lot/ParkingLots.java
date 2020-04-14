package com.blabz.parking_lot;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParkingLots<ParkingLotObserver> {
        ParkingLotOwner owner = new ParkingLotOwner();
        private int parkingLotCapacity;
        private List<ParkingLotObserver> observers;
        Map<Integer, Vehicle> vehicleSlotMap = new HashMap<>();
        public ParkingLots(int capacity){
        this.parkingLotCapacity = capacity;
        this.observers = new ArrayList<>();
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
        vehicleSlotMap.put(owner.getParkingSlot(),vehicle);
        return true;
        }
        public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicleSlotMap.containsValue(vehicle)) {
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
        }