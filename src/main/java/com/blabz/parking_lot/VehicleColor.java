package com.blabz.parking_lot;

public enum VehicleColor implements SearchableAttributes{
    WHITE{
    }, BLUE {
        @Override
        public Predicate<SlotInformation> getFilter() {
            return slot -> slot.getVehicle().getColor().equals(BLUE);
        }
    }, OTHER {
            @Override
            public Predicate<SlotInformation> getFilter() {
                return slot -> slot.getVehicle().getColor().equals(OTHER);
            }
    };

    public Predicate<SlotInformation> getFilter() {
        return slot -> slot.getVehicle().getColor().equals(WHITE);
    }
}
