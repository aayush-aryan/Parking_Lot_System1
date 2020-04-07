import com.blabz.parking_lot.ParkingLotSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem=null;
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
}
