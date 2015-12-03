/**
 * Created by ankitmishra on 02/12/15.
 */
public class ParkingManager implements ParkingObserver {
    public void notifyParkingFull() {
        this.putParkingFullSign();
    }

    @Override
    public void notifyParkingHasSpace() {
        this.removeParkingFullSign();
    }

    private void removeParkingFullSign() {
    }

    private void putParkingFullSign() {
        //DomeSomething.
    }
}
