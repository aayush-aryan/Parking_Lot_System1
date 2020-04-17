package com.blabz.parking_lot;

public class Vehicle {
        private final VehicleColor vehicleColor;
        private final VehicleMake make;
        private final String numberPlate;

        public Vehicle(String numberPlate, VehicleMake make, VehicleColor vehicleColor){
            this.vehicleColor = vehicleColor;
            this.numberPlate = numberPlate;
            this.make = make;
        }

    public Vehicle(String car, VehicleColor vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public VehicleColor getColor() {
            return vehicleColor;
        }
        public VehicleMake getMake() {
            return make;
        }
        public String getNumberPlate() {
            return this.numberPlate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vehicle vehicle = (Vehicle) o;
            return Objects.equals(numberPlate, vehicle.numberPlate) &&
                    Objects.equals(make, vehicle.make) &&
                    vehicleColor == vehicle.vehicleColor;
        }
}
