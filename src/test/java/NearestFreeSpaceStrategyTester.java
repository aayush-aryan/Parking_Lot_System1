import com.blabz.parking_lot.NearestFreeSpaceStrategy;
import com.blabz.parking_lot.ParkingLotException;
import com.blabz.parking_lot.ParkingLots;
import com.blabz.parking_lot.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NearestFreeSpaceStrategyTester {
    List<ParkingLots> parkingLotsList = new ArrayList<>();
    ParkingLots parkingLots;
    @Before
    public void setup(){
        parkingLots = new ParkingLots(4);
        for(int i=1; i<=4; i++)
            this.parkingLotsList.add(new ParkingLots(4));
    }
    @Mock
    NearestFreeSpaceStrategy parkingStrategy = new NearestFreeSpaceStrategy();
    @Test
    public void givenParkingLotWhenDriverWantsParkedCarShouldReturnFirstParkingLots() throws ParkingLotException, ParkingLotException {
        when(parkingStrategy.getParkingLot(parkingLotsList)).thenReturn(parkingLotsList.get(0));
        Assert.assertEquals(parkingStrategy.getParkingLot(parkingLotsList),parkingLotsList.get(0));
    }
    @Test
    public void IfDriverParksTWoCarsBothCarParkedShouldInFirstSlot() throws ParkingLotException {
        ParkingLots parkingLot1 = parkingLots.getParkingLot(parkingLotsList, DriverType.HANDICAPPED);
        parkingLot1.park(new Vehicle("car", vehicleColor));
        ParkingLots parkingLot2 = parkingLots.getParkingLot(parkingLotsList,DriverType.HANDICAPPED);
        parkingLot2.park(new Vehicle("car", vehicleColor));
        Integer[] expected = new Integer[]{Integer.valueOf(2), Integer.valueOf(2)};
        Integer[] actual = new Integer[]{parkingLot1.getNumOfVehicleParked(), parkingLot2.getNumOfVehicleParked()};
        Assert.assertArrayEquals(expected,actual);
    }
}
