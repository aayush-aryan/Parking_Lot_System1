package com.blabz.parking_lot;

public class ParkedVehicleDetails {
    private String attendantName;
    private Vehicle vehicle;
    private parkinglot.vehicles.VehicleSize vehicleSize;
    private DriverType driverType;

    public ParkedVehicleDetails(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public parkinglot.vehicles.VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public ParkedVehicleDetails(Vehicle vehicle, DriverType driverType, parkinglot.vehicles.VehicleSize vehicleSize) {
        this.driverType = driverType;
        this.vehicleSize = vehicleSize;
        this.vehicle = vehicle;
    }

    public VehicleColor getVehicleColor() {
        return this.vehicle.getColor();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getAttendantName() {
        return this.attendantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkedVehicleDetails details = (ParkedVehicleDetails) o;
        return Objects.equals(vehicle, details.vehicle);
    }
}
