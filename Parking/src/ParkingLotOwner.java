import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkingLotOwner {
    List<ParkingLot>  parkingLots;

    public ParkingLotOwner(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
