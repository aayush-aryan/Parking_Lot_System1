import com.blabz.parking_lot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkedVehicleDetailsTest {
        private ParkedVehicleDetails details,details1;
        private Vehicle vehicle,vehicle1;

        @Before
        public void setUp() throws Exception {
            this.vehicle = new Vehicle("BR01", VehicleMake.BMW,VehicleColor.WHITE);
            this.vehicle1 = new Vehicle("BR03", VehicleMake.SMALL_HANDICAP_CAR,VehicleColor.BLUE);
            this.details = new ParkedVehicleDetails(vehicle, DriverType.NORMAL, parkinglot.vehicles.VehicleSize.SMALL);
            this.details1 = new ParkedVehicleDetails(vehicle1, DriverType.HANDICAPPED, parkinglot.vehicles.VehicleSize.SMALL);
        }

        @Test
        public void givenARequestToGetDriverTypeShouldReturnDriverType() {
            DriverType driverType = details.getDriverType();
            Assert.assertEquals(DriverType.NORMAL, driverType);
        }

        @Test
        public void givenARequestToGetVehicleSizeShouldReturnVehicleSize() {
            VehicleSize vehicleSize = details.getVehicleSize();
            Assert.assertEquals(parkinglot.vehicles.VehicleSize.SMALL, vehicleSize);
        }

        @Test
        public void givenARequestToGetVehicleColourShouldReturnVehicleColor() {
            VehicleColor vehicleColor = details.getVehicleColor();
            Assert.assertEquals(VehicleColor.WHITE, vehicleColor);
        }
        @Test
        public void givenARequestToGetVehicleMakeShouldReturnVehicleColor() {
            VehicleMake vehicleMake = details.getVehicleMake();
            Assert.assertEquals(VehicleMake.BMW, vehicleMake);
        }
        @Test
        public void givenARequestToGetVehicleNumberPlateShouldReturnVehicleNumberPlater() {
            String vehicleNumberPlate = details.getNumberPlate();
            Assert.assertEquals("BR01", vehicleNumberPlate);
        }
        @Test
        public void givenARequestToGetHandicapCarShouldReturnHandicapCar() {
            VehicleMake vehicleMake = details1.getVehicleMake();
            Assert.assertEquals(VehicleMake.SMALL_HANDICAP_CAR, vehicleMake);
        }
}
