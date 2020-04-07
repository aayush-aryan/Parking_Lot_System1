import com.blabz.parking_lot.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
       public ParkingLotSystem parkingLotSystem=null;
        Object vehicle=null;

        @Before
        public void setUp() throws Exception{
            parkingLotSystem=new ParkingLotSystem();
            vehicle=new Object();
        }
        @Test
        public void givenAVehicle_WhenParked_shouldReturnTrue(){
            boolean isParked=parkingLotSystem.park(new Object());
            Assert.assertTrue(isParked);
        }
        @Test
        public void givenAVehicle_TobeParked_whenSpaceOccupied_shouldReturnFalse(){
            parkingLotSystem.park(vehicle);
            boolean isParked=parkingLotSystem.park(new Object());
            Assert.assertFalse(isParked);
        }
        @Test
        public void givenAVehicle_When_UnParked_Should_ReturnTrue() {
            parkingLotSystem.park(vehicle);
            boolean isUnParked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnParked);
        }
        @Test
        public void givenAVehicle_TriedTo_UnPark_ButNoCarInSpot_Should_ReturnFalse() {
            boolean isUnParked = parkingLotSystem.unPark(new Object());
            Assert.assertFalse(isUnParked);
        }

        @Test
        public void givenAVehicle__UnParked_Should_ReturnTrue() {
            parkingLotSystem.park(vehicle);
            Object vehicle1=new Object();
            boolean isUnParked = parkingLotSystem.unPark(vehicle1);
            Assert.assertFalse(isUnParked);
        }
}
