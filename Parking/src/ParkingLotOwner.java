import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkingLotOwner {
    List<ParkingAreaBase> parkingLots;

    public ParkingLotOwner(List<ParkingAreaBase> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingAreaBase> getParkingLots() {
        return parkingLots;
    }
}
