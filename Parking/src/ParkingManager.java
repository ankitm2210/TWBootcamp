/**
 * Created by ankitmishra on 02/12/15.
 */
public class ParkingManager implements ParkingObserver {
    public void notifyParkingFull(ParkingLot parkingLot) {
        this.putParkingFullSign();
    }

    @Override
    public void notifyParkingHasSpace(ParkingLot parkingLot) {
        this.removeParkingFullSign();
    }

    private void removeParkingFullSign() {
    }

    private void putParkingFullSign() {
        //DomeSomething.
    }
}
