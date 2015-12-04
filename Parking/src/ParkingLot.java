import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ankitmishra on 02/12/15.
 */
public class ParkingLot extends ParkingAreaBase {

    private Map<ParkingToken, Car> parkingSpaces = new HashMap<ParkingToken, Car>();

    public ParkingLot(int capacity) {
        super(capacity);
        parkingObservers = new ArrayList<ParkingObserver>();
    }

    public ParkingLot(int capacity, double distance) {
        super(capacity, distance);
        parkingObservers = new ArrayList<ParkingObserver>();
    }

    public ParkingLot(int capacity, double distance, double cost) {
        super(capacity, distance, cost);
        parkingObservers = new ArrayList<ParkingObserver>();
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public int getAvailableSpaces() {
        return this.capacity - this.parkingSpaces.size();
    }

    @Override
    public ParkingToken parkCar(Car car) {
        if (IsParkingAvailable() && isCarAlreadyParked(car) == false) {
            ParkingToken token = new ParkingToken(car.getRegNumber());
            parkingSpaces.put(token, car);
            if (isFull()) {
                for (ParkingObserver observer : parkingObservers) {
                    observer.notifyParkingFull(this);
                }
            }

            return token;
        }

        return null;
    }

    @Override
    public Car unparkCar(ParkingToken token) throws CarNotFoundException {
        if (parkingSpaces.containsKey(token) == false) {
            throw new CarNotFoundException();
        }

        Car unparkedCar = parkingSpaces.get(token);
        if(isFull())
        {
            for (ParkingObserver observer : parkingObservers) {
                observer.notifyParkingHasSpace(this);
            }
        }

        parkingSpaces.remove(token);
        return unparkedCar;
    }

    public boolean IsParkingAvailable() {
        return parkingSpaces.size() < this.capacity;
    }

    public void attachObserver(ParkingObserver observer) {
        this.parkingObservers.add(observer);
    }

    public void detachObserver(ParkingObserver observer) {
        if (this.parkingObservers.contains(observer)) {
            this.parkingObservers.remove(observer);
        }
    }

    public boolean isCarAlreadyParked(Car car) {
        ParkingToken token = new ParkingToken(car.getRegNumber());
        return parkingSpaces.keySet().contains(token);
    }

    public boolean isFull() {
        return !this.IsParkingAvailable();
    }
}
