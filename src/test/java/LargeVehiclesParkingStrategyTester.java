import com.blabz.parking_lot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static javafx.beans.binding.Bindings.when;

public class LargeVehiclesParkingStrategyTester<LargeVehiclesParkingStrategy> {

    @RunWith(MockitoJUnitRunner.class)
    List<ParkingLots> parkingLotsList = new ArrayList<>();
        ParkingLots parkingLots;
        @Before
        public void setup(){
            parkingLots = new ParkingLots(4);
            for(int i=1; i<=4; i++)
                this.parkingLotsList.add(new ParkingLots(4));
        }
        @Mock
        LargeVehiclesParkingStrategy parkingStrategy = new LargeVehiclesParkingStrategy();
        @Test
        public void givenParkingLotWhenDriverWantsParkedCarShouldReturnFirstParkingLots() throws ParkingLotException {
            when(parkingStrategy.getParkingLot(parkingLotsList)).thenReturn(parkingLotsList.get(0));
            Assert.assertEquals(parkingStrategy.getParkingLot(parkingLotsList),parkingLotsList.get(0));
        }
        @Test
        public void IfDriverParksTWoCarsBothCarParkedShouldInFirstSlot() throws ParkingLotException {
            ParkingLots parkingLot1 = parkingLots.getParkingLot(parkingLotsList, parkinglot.vehicles.VehicleSize.LARGE);
            parkingLot1.park(new Vehicle("BR02", VehicleMake.BMW, VehicleColor.WHITE));
            ParkingLots parkingLot2 = parkingLots.getParkingLot(parkingLotsList, parkinglot.vehicles.VehicleSize.LARGE);
            parkingLot2.park(new Vehicle("BR05", VehicleMake.BMW, VehicleColor.BLUE));
            Integer[] expected = new Integer[]{Integer.valueOf(1), Integer.valueOf(1)};
            Integer[] actual = new Integer[]{parkingLot1.getNumOfVehicleParked(), parkingLot2.getNumOfVehicleParked()};
            Assert.assertArrayEquals(expected,actual);
        }
}
