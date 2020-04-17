import com.blabz.parking_lot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Object car;
    ParkingLotOwner owner = new ParkingLotOwner();
    AirportSecurity security = new AirportSecurity();

    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
        car = new Object();
    }

    @Test
    public void givenCarWhenParkedInParkingLotShouldReturnTrue() {
        try {
            parkingLotSystem.park(car);
            boolean isParked = parkingLotSystem.isThisVehiclePresentInParkingLot(car);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCarWhenFirstParkedAndThenUnParkedShouldReturnFalse() {
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(car);
            boolean result = parkingLotSystem.isThisVehiclePresentInParkingLot(car);
            Assert.assertFalse(result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCarWhenTryToUnParkedEvenWhenItWasNotParkedShouldThrowException() {
        try {
            Object car2 = new Object();
            parkingLotSystem.unPark(car2);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED, e.type);
        }
    }

    @Test
    public void givenCarIfTryToReParkShouldThrowAnException() {
        try {
            parkingLotSystem.park(car);
            parkingLotSystem.park(car);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenParkingLotWhenParkingLotGetFullShouldInformParingOwner() {
        try {
            parkingLotSystem.registeredParkingLotObserver(owner);
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            boolean isParingLotFull = owner.isParkingLotFull();
            Assert.assertTrue(isParingLotFull);
        }
    }

    @Test
    public void givenParkingLotWhenParkingLotGetFullShouldInformAirportSecurity() {
        try {
            parkingLotSystem.registeredParkingLotObserver(security);
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            boolean isParingLotFull = security.isParkingLotFull();
            Assert.assertTrue(isParingLotFull);
        }
    }

    @Test
    public void givenParkingLotWhenParkingLotGetEmptyAfterFullShouldInformOwner() {
        try {
            parkingLotSystem.registeredParkingLotObserver(owner);
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Object());
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
        }
        try {
            parkingLotSystem.unPark(car);
            boolean lotEmpty = owner.isParkingLotEmpty();
            Assert.assertTrue(lotEmpty);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotHavingAttendantShouldBeAbleToParkCar() {
        try {
            parkingLotSystem.registeredParkingLotObserver(owner);
            ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(car);
            parkingLotSystem.getParkingLotAttendant(parkingLotAttendant);
            boolean isParked = parkingLotSystem.isThisVehiclePresentInParkingLot(car);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotHavingAttendantWhenParkingLotFullShouldThrowException() {
        try {
            parkingLotSystem.registeredParkingLotObserver(owner);
            ParkingLotAttendant parkingLotAttendant1 = new ParkingLotAttendant(car);
            ParkingLotAttendant parkingLotAttendant2 = new ParkingLotAttendant(new Object());
            ParkingLotAttendant parkingLotAttendant3 = new ParkingLotAttendant(new Object());
            parkingLotSystem.getParkingLotAttendant(parkingLotAttendant1);
            parkingLotSystem.getParkingLotAttendant(parkingLotAttendant2);
            parkingLotSystem.getParkingLotAttendant(parkingLotAttendant3);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL, e.type);
        }
    }

    @Test
    public void givenParkingLotIfParkedVehicleHaveSameVehicleShouldReturnVehicleAttendant() {
        try {
            parkingLotSystem.registeredParkingLotObserver(owner);
            ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(car);
            parkingLotSystem.getParkingLotAttendant(parkingLotAttendant);
            ParkingLotAttendant myVehicleAttendant = parkingLotSystem.getMyVehicle(parkingLotAttendant);
            Assert.assertEquals(parkingLotAttendant, myVehicleAttendant);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenParkingLotIfAttendantNotHaveParkedVehicleShouldThrowException() {
        try {
            parkingLotSystem.registeredParkingLotObserver(owner);
            ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant(car);
            parkingLotSystem.getParkingLotAttendant(parkingLotAttendant);
            ParkingLotAttendant myVehicleAttendant = parkingLotSystem.getMyVehicle(new ParkingLotAttendant(new Object()));
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE_PARKED, e.type);
        }
    }
}