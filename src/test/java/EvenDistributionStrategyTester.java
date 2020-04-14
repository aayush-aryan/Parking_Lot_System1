import com.blabz.parking_lot.EvenDistributionParkingStrategy;
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
public class EvenDistributionStrategyTester {
    List<ParkingLots> parkingLotsList = new ArrayList<>();
    ParkingLots parkingLots;
    @Before
    public void setup(){
        parkingLots = new ParkingLots(4);
        for(int i=0; i<3; i++)
            this.parkingLotsList.add(new ParkingLots(4));
    }
    @Mock
    EvenDistributionParkingStrategy parkingStrategy = new EvenDistributionParkingStrategy();
    @Test
    public void givenParkingLotWhenDriverWantsParkedCarShouldReturnFirstParkingLots() throws ParkingLotException {
        when(parkingStrategy.getParkingLot(parkingLotsList)).thenReturn(parkingLotsList.get(0));
        Assert.assertEquals(parkingStrategy.getParkingLot(parkingLotsList),parkingLotsList.get(0));
    }

    private Object when(ParkingLots parkingLot) {
    }

    @Test
    public void IfDriverParksThreeCarsEachParkingSlotShouldBeOccupiedByOne() throws ParkingLotException {
            ParkingLots parkingLot1 = parkingLots.getParkingLot(parkingLotsList);
            parkingLot1.park(new Vehicle("car"));
            ParkingLots parkingLot2 = parkingLots.getParkingLot(parkingLotsList);
            parkingLot2.park(new Vehicle("car"));
            Integer[] expected = new Integer[]{Integer.valueOf(1), Integer.valueOf(1)};
            Integer[] actual = new Integer[]{parkingLot1.getNumOfVehicleParked(), parkingLot2.getNumOfVehicleParked()};
            Assert.assertArrayEquals(expected,actual);
         }
    }
