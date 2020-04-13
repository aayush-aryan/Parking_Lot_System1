import com.blabz.parking_lot.ParkingLotException;
import com.blabz.parking_lot.ParkingLotSystem;
import com.blabz.parking_lot.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
        ParkingLotSystem parkingLotSystem;
        Object car;

        @Before
        public void setUp() {
            this.parkingLotSystem = new ParkingLotSystem();
            parkingLotSystem.setParkingLotCapacity(2);
            this.car = new Object();
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
        public void givenParkingLotWithASizeWhenCapacityIsFullShouldThrowAnException() {
            try {
                parkingLotSystem.park(car);
                Object car2 = new Object();
                parkingLotSystem.park(car2);
                Object car3 = new Object();
                parkingLotSystem.park(car3);
            } catch (ParkingLotException e) {
                Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL, e.type);
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
        public void whenParkingCapacityIsFullAndOwnerIsInformedAboutItShouldReturnTrue() {
            try {
                this.parkingLotSystem.park(car);
                Object car2 = new Object();
                parkingLotSystem.park(car2);
                Object car3 = new Object();
                parkingLotSystem.park(car3);
            } catch (ParkingLotException e) {
                Assert.assertEquals(ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL, e.type);
                Assert.assertTrue(this.parkingLotSystem.parkingOwner.isParkingLotFull);
            }
        }
}
