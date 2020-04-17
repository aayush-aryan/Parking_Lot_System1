import com.blabz.parking_lot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotTest {
        List<ParkingLots> parkingLotsList = new ArrayList<>();
        ParkingLots parkingLots;
        Vehicle car;
        ParkingLotOwner owner;
        @Before
        public void setupParkingLot(){
            this.parkingLots = new ParkingLots(4);
            this.car = new Vehicle("BR01", VehicleMake.BMW, VehicleColor.OTHER);
            this.owner = new ParkingLotOwner();
            parkingLots.registeredParkingLotObserver(owner);
            for(int i=1; i<=4; i++)
                this.parkingLotsList.add(new ParkingLots(4));
        }

        @Test
        public void policeDepartmentWantToKnowLocationOfWhiteCarShouldReturnLocation() {
            try {
                Vehicle whiteCar1 = new Vehicle("BR03",VehicleMake.BMW,VehicleColor.WHITE);
                Vehicle whiteCar2 = new Vehicle("BR04", VehicleMake.OTHER, VehicleColor.WHITE);
                Vehicle whiteCar3 = new Vehicle("BR05", VehicleMake.TOYOTA,VehicleColor.OTHER);
                parkingLots.park(whiteCar1);
                parkingLots.park(whiteCar3);
                parkingLots.park(whiteCar2);
                List<Integer> expectedResult = new ArrayList<>();
                expectedResult.add(1);
                expectedResult.add(3);
                Assert.assertEquals(expectedResult, parkingLots.getLocationOfWhiteCars());
            } catch (ParkingLotException e) {
                e.printStackTrace();
            }
        }
        @Test
        public void policeDepartmentWantToKnowLocationOfWhiteCarIfNotPresentShouldReturnZeroSizeList() {
            try {
                Vehicle whiteCar3 = new Vehicle("BR06",VehicleMake.BMW,VehicleColor.OTHER);
                parkingLots.park(whiteCar3);
                List<Integer> expectedResult = new ArrayList<>();
                Assert.assertEquals(0,parkingLots.getLocationOfWhiteCars().size());
            } catch (ParkingLotException e) {
                e.printStackTrace();
            }
        }
        @Test
        public void policeDepartmentWantToKnowLocationOfAllBlueToyotaCarShouldReturnLocation() {
            try{
                Vehicle blueToyotaCar1 = new Vehicle("BR03",VehicleMake.TOYOTA,VehicleColor.BLUE);
                Vehicle blueToyotaCar2 = new Vehicle("BR05", VehicleMake.TOYOTA,VehicleColor.BLUE);
                Vehicle whiteToyotaCar = new Vehicle("BR09", VehicleMake.TOYOTA,VehicleColor.WHITE);
                Vehicle blueBmwCar = new Vehicle("BR09", VehicleMake.BMW,VehicleColor.BLUE);
                parkingLots.park(blueToyotaCar1);
                parkingLots.park(blueBmwCar);
                parkingLots.park(whiteToyotaCar);
                parkingLots.park(blueToyotaCar2);
                List<Integer> expectedResult = new ArrayList<>();
                expectedResult.add(1);
                expectedResult.add(4);
                Assert.assertEquals(expectedResult, parkingLots.getLocationOfBlueToyotaCars());
            } catch (ParkingLotException e) {
                e.printStackTrace();
            }
        }
        @Test
        public void policeDepartmentWantToKnowPlateNumberOfAllBlueToyotaCarShouldReturnPlateNumber() {
            try{
                Vehicle blueToyotaCar1 = new Vehicle("BR03",VehicleMake.TOYOTA,VehicleColor.BLUE);
                Vehicle blueToyotaCar2 = new Vehicle("BR05", VehicleMake.TOYOTA,VehicleColor.BLUE);
                Vehicle whiteToyotaCar = new Vehicle("BR09", VehicleMake.TOYOTA,VehicleColor.WHITE);
                Vehicle blueBmwCar = new Vehicle("BR09", VehicleMake.BMW,VehicleColor.BLUE);
                parkingLots.park(blueToyotaCar1);
                parkingLots.park(blueBmwCar);
                parkingLots.park(whiteToyotaCar);
                parkingLots.park(blueToyotaCar2);
                List<String> expectedResult = new ArrayList<>();
                expectedResult.add("BR03");
                expectedResult.add("BR05");
                Assert.assertEquals(expectedResult, parkingLots.getPlateNumberOfBlueToyotaCars());
            } catch (ParkingLotException e) {
                e.printStackTrace();
            }
        }
        @Test
        public void policeDepartmentWantToKnowParkingAttendantNameOfAllBlueToyotaCarShouldReturnName() {
            try{
                ParkedVehicleDetails details = new ParkedVehicleDetails(car);
                details.setAttendantName("Rajesh Chauhan");
                Vehicle blueToyotaCar1 = new Vehicle("BR03",VehicleMake.TOYOTA,VehicleColor.BLUE);
                Vehicle blueToyotaCar2 = new Vehicle("BR05", VehicleMake.TOYOTA,VehicleColor.BLUE);
                Vehicle whiteToyotaCar = new Vehicle("BR09", VehicleMake.TOYOTA,VehicleColor.WHITE);
                Vehicle blueBmwCar = new Vehicle("BR09", VehicleMake.BMW,VehicleColor.BLUE);
                parkingLots.park(blueToyotaCar1);
                parkingLots.park(blueBmwCar);
                parkingLots.park(whiteToyotaCar);
                parkingLots.park(blueToyotaCar2);
                Assert.assertEquals("Rajesh Chauhan", details.getAttendantName());
            } catch (ParkingLotException e) {
                e.printStackTrace();
            }
        }
}
