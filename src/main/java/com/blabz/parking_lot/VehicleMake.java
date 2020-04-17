package com.blabz.parking_lot;

import java.util.function.Predicate;

public enum VehicleMake implements SearchableAttributes{
        BMW {
            @Override
            public Predicate<SlotInformation> getFilter() {
                return slot -> slot.getVehicle().getMake().equals(BMW);
            }
        }, TOYOTA {
            @Override
            public Predicate<SlotInformation> getFilter() {
                return slot -> slot.getVehicle().getMake().equals(TOYOTA);
            }
        }, SMALL_HANDICAP_CAR {
            @Override
            public Predicate<SlotInformation> getFilter() {
                return slot -> slot.getVehicle().getMake().equals(SMALL_HANDICAP_CAR);
            }
        },
        OTHER {
            @Override
            public Predicate<SlotInformation> getFilter() {
                return slot -> slot.getVehicle().getMake().equals(OTHER);
            }
        }
}
