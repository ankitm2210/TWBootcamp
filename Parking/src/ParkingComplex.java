import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkingComplex extends ParkingAreaBase {
    List<ParkingAreaBase> parkingLots;
    Map<ParkingToken, ParkingAreaBase> parkingTokenToLotMap;

    public ParkingComplex(List<ParkingAreaBase> parkingLots, double cost, double distance) {
        super(distance, cost);
        this.parkingLots = parkingLots;
        this.parkingTokenToLotMap = new HashMap<ParkingToken, ParkingAreaBase>();
    }

    public List<ParkingAreaBase> getParkingLots() {
        return parkingLots;
    }

    @Override
    double getDistance() {
        return this.distance;
    }

    @Override
    double getCost() {
        return this.cost;
    }

    @Override
    int getAvailableSpaces() {
        int totalAvailableSpaces = 0;
        for (ParkingAreaBase parkingLot : this.parkingLots) {
            totalAvailableSpaces += parkingLot.getAvailableSpaces();
        }

        return totalAvailableSpaces;
    }

    @Override
    ParkingToken parkCar(Car car) {
        ParkingStrategy parkingStrategy = new ParkLotWithMaxSpaceStrategy();
        ParkingAreaBase suitableParkingLot = parkingStrategy.getParklotStrategy(this.parkingLots);
        ParkingToken token = suitableParkingLot.parkCar(car);
        this.parkingTokenToLotMap.put(token, suitableParkingLot);
        return token;
    }

    @Override
    Car unparkCar(ParkingToken token) throws CarNotFoundException {
        if (!this.parkingTokenToLotMap.containsKey(token)) {
            throw new CarNotFoundException();
        }

        ParkingAreaBase suitableParkingLot = this.parkingTokenToLotMap.get(token);
        this.parkingTokenToLotMap.remove(token);

        return suitableParkingLot.unparkCar(token);
    }
}
