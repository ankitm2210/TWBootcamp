import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ankitmishra on 02/12/15.
 */
public class ParkingLot{

    private final int capacity;
    private List<ParkingObserver> parkingObservers;

    private Map<ParkingToken, Car> parkingSpaces = new HashMap<ParkingToken, Car>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        parkingObservers = new ArrayList<ParkingObserver>();
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

    public ParkingToken parkCar(Car car) {
        if (IsParkingAvailable() && isCarAlreadyParked(car) == false) {
            ParkingToken token = new ParkingToken(car.getRegNumber());
            parkingSpaces.put(token, car);
            if (isFull()) {
                for (ParkingObserver observer : parkingObservers) {
                    observer.notifyParkingFull();
                }
            }

            return token;
        }

        return null;
    }

    public Car unparkCar(ParkingToken token) throws CarNotFoundException {
        if (parkingSpaces.containsKey(token) == false) {
            throw new CarNotFoundException();
        }

        Car unparkedCar = parkingSpaces.get(token);
        if(isFull())
        {
            for (ParkingObserver observer : parkingObservers) {
                observer.notifyParkingHasSpace();
            }
        }

        parkingSpaces.remove(token);
        return unparkedCar;
    }

    public boolean isFull() {
        return !this.IsParkingAvailable();
    }
}
