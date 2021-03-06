
import com.blabz.parking_lot.EvenDistributionParkingStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import parkinglot.exception.ParkingLotException;
import parkinglot.parkinglotcomponent.ParkingLots;
import parkinglot.parkingstrategy.EvenDistributionParkingStrategy;
import parkinglot.vehicles.DriverType;
import parkinglot.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvenDistributionStrategyTester {
    List<ParkingLots> parkingLotsList = new ArrayList<>();
    ParkingLots parkingLots;
    @Before
    public void setup(){
        parkingLots = new ParkingLots(4);
        for(int i=1; i<=4; i++)
            this.parkingLotsList.add(new ParkingLots(4));
    }
    @Mock
    EvenDistributionParkingStrategy parkingStrategy = new EvenDistributionParkingStrategy();
    @Test
    public void givenParkingLotWhenDriverWantsParkedCarShouldReturnFirstParkingLots() throws ParkingLotException {
        when(parkingStrategy.getParkingLot(parkingLotsList)).thenReturn(parkingLotsList.get(0));
        Assert.assertEquals(parkingStrategy.getParkingLot(parkingLotsList),parkingLotsList.get(0));
    }
    @Test
    public void IfDriverParksTwoCarsEachParkingSlotShouldBeOccupiedByOne() throws ParkingLotException {
            ParkingLots parkingLot1 = parkingLots.getParkingLot(parkingLotsList, DriverType.HANDICAPPED);
            parkingLot1.park(new Vehicle("car"));
            ParkingLots parkingLot2 = parkingLots.getParkingLot(parkingLotsList,DriverType.HANDICAPPED);
            parkingLot2.park(new Vehicle("car"));
            Integer[] expected = new Integer[]{Integer.valueOf(1), Integer.valueOf(1)};
            Integer[] actual = new Integer[]{parkingLot1.getNumOfVehicleParked(), parkingLot2.getNumOfVehicleParked()};
            Assert.assertArrayEquals(expected,actual);
         }
    }
