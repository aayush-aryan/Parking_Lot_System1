import com.blabz.parking_lot.ParkingLotException;
import com.blabz.parking_lot.ParkingLotOwner;
import com.blabz.parking_lot.ParkingLots;
import com.blabz.parking_lot.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ParkingLotSystemTest {
    ParkingLots parkingLots;
    Vehicle car;
    ParkingLotOwner owner;

    @Before
    public void setupParkingLot() {
        this.parkingLots = new ParkingLots(4);
        this.car = new Vehicle("car");
        this.owner = new ParkingLotOwner();
        parkingLots.registeredParkingLotObserver(owner);
    }

    @Test
    public void givenParkingLotDriverWantsToKnowParkingLotAvailability() {
        int availability = parkingLots.getAvailability();
        Assert.assertEquals(4, availability);
    }

    @Test
    public void givenParkingLotWhenDriverParkedTheCarParkingLotAvailabilityShouldBeDecreased() {
        try {
            parkingLots.park(car);
            int availability = parkingLots.getAvailability();
            Assert.assertEquals(3, availability);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotWhenDriverUnParkedTheCarParkingLotAvailabilityShouldBeIncreased() {
        try {
            parkingLots.park(car);
            parkingLots.unPark(car);
            int availability = parkingLots.getAvailability();
            Assert.assertEquals(4, availability);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotWhenDriverWantsParkedSameCarTwoTimeShouldThrowException() {
        try {
            parkingLots.park(car);
            parkingLots.park(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenParkingLotWhenDriverWantsUnParkedCarEvenHeNotParkedShouldThrowException() {
        try {
            parkingLots.unPark(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED, e.type);
        }
    }

    @Test
    public void givenParkingLotIfFullShouldThrowException() {
        try {
            parkingLots.park(car);
            parkingLots.park(new Vehicle("car"));
            parkingLots.park(new Vehicle("car"));
            parkingLots.park(new Vehicle("car"));
            parkingLots.park(new Vehicle("car"));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL, e.type);
        }
    }

    @Test
    public void givenParkingLotDriverWantsToFindCarIfCarPresentShouldReturnTrue() {
        try {
            parkingLots.park(car);
            boolean isPresent = parkingLots.getMyVehicle(car);
            Assert.assertTrue(isPresent);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotDriverWantsToFindCarIfCarNotPresentShouldThrowException() {
        try {
            parkingLots.getMyVehicle(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED, e.type);
        }
    }
    @Test
    public void givenParkingLotWhenACarIsParkedItShouldBePresentInParkingTimeAndDate() {
        try {
            parkingLots.park(car);
            LocalDateTime vehicleTimeTable1 = parkingLots.getVehicleTimeTable(car);
            TimeUnit.SECONDS.sleep(3);
            Vehicle car1 = new Vehicle("car");
            parkingLots.park(car1);
            LocalDateTime vehicleTimeTable2 = parkingLots.getVehicleTimeTable(car1);
            Assert.assertEquals(vehicleTimeTable1.getSecond()+3,vehicleTimeTable2.getSecond());
            Assert.assertEquals(vehicleTimeTable1.getDayOfWeek(),vehicleTimeTable2.getDayOfWeek());
        } catch (ParkingLotException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenParkingLotWhenACarIsNotParkedIt_ShouldNotBePresentInTimeTable() {
        try {
            LocalDateTime vehicleTimeTable1 = parkingLots.getVehicleTimeTable(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED,e.type);
        }
    }
}