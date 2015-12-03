import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkNearestStrategy implements ParkingStrategy{
    @Override
    public ParkingLot getParklotStrategy(List<ParkingLot> availableParkingLots) {
        ParkingLot assignedParkingLot=availableParkingLots.get(0);
        for(ParkingLot parkingLot:availableParkingLots){
            if(parkingLot.getDistance()<assignedParkingLot.getDistance()){
                assignedParkingLot =parkingLot;
            }
        }
        return assignedParkingLot;
    }
}
