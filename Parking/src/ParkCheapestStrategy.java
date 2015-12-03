import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkCheapestStrategy implements ParkingStrategy{
    @Override
    public ParkingLot getParklotStrategy(List<ParkingLot> availableParkingLots) {
        ParkingLot assignedParkingLot=availableParkingLots.get(0);
        for(ParkingLot parkingLot:availableParkingLots){
            if(parkingLot.getCost()<assignedParkingLot.getCost()){
                assignedParkingLot =parkingLot;
            }
        }
        return assignedParkingLot;
    }
}

