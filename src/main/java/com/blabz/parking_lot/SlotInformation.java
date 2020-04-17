package com.blabz.parking_lot;

import java.time.LocalDateTime;
import java.util.Objects;

public class SlotInformation{
        private ParkedVehicleDetails vehicleDetails;
        private LocalDateTime parkingStartTime;
        private int slotNumber;

        public SlotInformation(ParkedVehicleDetails vehicleDetails) {
            this.vehicleDetails = vehicleDetails;
        }

        public SlotInformation(int slot, ParkedVehicleDetails vehicle, LocalDateTime parkingStartTime) {
            this.slotNumber = slot;
            this.vehicleDetails = vehicle;
            this.parkingStartTime = parkingStartTime;
        }

        public LocalDateTime getParkingStartTime() {
            return parkingStartTime;
        }

        public VehicleColor getVehicleColor() {
            return this.vehicleDetails.getVehicleColor();
        }

        public Vehicle getVehicle() { return this.vehicleDetails.getVehicle(); }

        public int getSlotNumber() {
            return this.slotNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SlotInformation slot = (SlotInformation) o;
            return Objects.equals(vehicleDetails, slot.vehicleDetails);
        }

        public ParkedVehicleDetails getDetails() {
            return this.vehicleDetails;
        }
}
