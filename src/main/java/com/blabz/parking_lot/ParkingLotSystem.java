package com.blabz.parking_lot;

import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem{
        protected ParkingLotOwner parkingOwner;
        private List parkedVehicles;
        private int parkingLotCapacity;
        private boolean parkingLotCapacityFull;
        private List<ParKingLotObserver> observers;

        public ParkingLotSystem() {
                this.parkedVehicles = new ArrayList();
                this.parkingOwner = new ParkingLotOwner();

        }
        public void setParkingLotCapacity(int capacity) {
                this.parkingLotCapacity = capacity;
        }
        public void registeredParkingLotObserver(ParKingLotObserver observer){
                this.observers.add(observer);
        }
        public void park(Object vehicle ) throws ParkingLotException {
                if (this.parkedVehicles.size() == this.parkingLotCapacity) {
                        this.parkingLotCapacityFull = true;
                        this.parkingOwner.setParkingLotCapacityFull();
                        throw new ParkingLotException("Sorry, Parking Lot Is Full",
                                ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL);
                }
                if (this.isThisVehiclePresentInParkingLot(vehicle)) {
                        throw new ParkingLotException("Car already present in parking lot!",
                                ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED);
                }
                this.parkedVehicles.add(vehicle);

        }
        public void unPark(Object vehicle) throws ParkingLotException {
                        if (this.isThisVehiclePresentInParkingLot(vehicle)) {
                                this.parkedVehicles.remove(vehicle);
                                return;
                        }
                        throw new ParkingLotException("No such car is present in parking lot",
                                ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED);
                }
                public boolean isThisVehiclePresentInParkingLot(Object vehicle) {
                        if (this.parkedVehicles.contains(vehicle))
                                return true;
                        return false;
                }
}
