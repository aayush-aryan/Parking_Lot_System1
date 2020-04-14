import com.blabz.parking_lot.DriverType;
import com.blabz.parking_lot.ParkedVehicleDetails;
import com.blabz.parking_lot.Vehicle;
import com.blabz.parking_lot.VehicleColor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkedVehicleDetailsTest {
    private ParkedVehicleDetails details;
    private Vehicle vehicle;
    @Before
    public void setUp() throws Exception {
        this.vehicle = new Vehicle("A", VehicleColor.WHITE);
        this.details = new ParkedVehicleDetails(vehicle, DriverType.NORMAL, parkinglot.vehicles.VehicleSize.SMALL);
    }
    @Test
    public void givenARequestToGetDriverTypeShouldReturnDriverType() {
        DriverType driverType = details.getDriverType();
        Assert.assertEquals(DriverType.NORMAL, driverType);
    }
    @Test
    public void givenARequestToGetVehicleSizeShouldReturnVehicleSize() {
        parkinglot.vehicles.VehicleSize vehicleSize = details.getVehicleSize();
        Assert.assertEquals(parkinglot.vehicles.VehicleSize.SMALL, vehicleSize);
    }
    @Test
    public void givenARequestToGetVehicleColourShouldReturnVehicleColor() {
        VehicleColor vehicleColor = details.getVehicleColor();
        Assert.assertEquals(VehicleColor.WHITE, vehicleColor);
    }
}
