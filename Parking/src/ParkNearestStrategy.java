import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkNearestStrategy implements ParkingStrategy {
    @Override
    public ParkingAreaBase getParklotStrategy(List<ParkingAreaBase> availableParkingLots) {
        ParkingAreaBase assignedParkingLot = availableParkingLots.get(0);
        for (ParkingAreaBase parkingLot : availableParkingLots) {
            if(parkingLot.getDistance()<assignedParkingLot.getDistance()){
                assignedParkingLot =parkingLot;
            }
        }
        return assignedParkingLot;
    }
}
