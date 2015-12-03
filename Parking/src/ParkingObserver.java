/**
 * Created by ankitmishra on 02/12/15.
 */
public interface ParkingObserver {
    void notifyParkingFull(ParkingLot parkingLot);

    void notifyParkingHasSpace(ParkingLot parkingLot);
}
