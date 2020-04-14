import com.blabz.parking_lot.ParkingLotException;
import com.blabz.parking_lot.ParkingLots;
import com.blabz.parking_lot.ParkingStrategy;
import java.util.Comparator;
public class LargeVehiclesParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingLots getParkingLot(List<ParkingLots> parkingLotsList) throws ParkingLotException {
        return parkingLotsList.stream()
                .filter(lot -> lot.getNumOfVehicleParked() != lot.getCapacity())
                .sorted(Comparator.comparing(lot -> (lot.getAvailability()), Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(() -> new ParkingLotException("No Any Lots Available",
                        ParkingLotException.ExceptionType.PARKING_CAPACITY_FULL));
    }
    }
