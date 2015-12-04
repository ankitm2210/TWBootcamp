import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public abstract class ParkingAreaBase {

    protected double distance;
    protected double cost;
    protected int capacity;
    protected List<ParkingObserver> parkingObservers;

    public ParkingAreaBase(int capacity) {
        this.capacity = capacity;
    }

    public ParkingAreaBase(double distance, double cost) {
        this.cost = cost;
        this.distance = distance;
    }

    public ParkingAreaBase(int capacity, double distance) {
        this.capacity = capacity;
        this.distance = distance;
    }

    public ParkingAreaBase(int capacity, double distance, double cost) {
        this.capacity = capacity;
        this.distance = distance;
        this.cost = cost;
    }

    abstract double getDistance();

    abstract double getCost();

    abstract int getAvailableSpaces();

    abstract ParkingToken parkCar(Car car);

    abstract Car unparkCar(ParkingToken token) throws CarNotFoundException;
}
