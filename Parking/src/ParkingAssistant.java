import java.util.*;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkingAssistant implements ParkingObserver{
    List<ParkingLot> parkingLots;
    List<ParkingLot> availableParkingLots;
    Map<ParkingToken, ParkingLot> tokenLotMap;

    public ParkingAssistant()
    {
        parkingLots = new ArrayList<>();
        tokenLotMap = new HashMap<ParkingToken, ParkingLot>();
    }

    public ParkingAssistant(List<ParkingLot> parkingLots) {
        this.parkingLots=parkingLots;
        this.availableParkingLots = new ArrayList<>();
        this.availableParkingLots.addAll(parkingLots);
        tokenLotMap = new HashMap<ParkingToken, ParkingLot>();
    }

    public boolean add(ParkingLot pl2) {
        if(this.parkingLots.contains(pl2))
        {
            return false;
        }

        this.parkingLots.add(pl2);
        return true;
    }

    public ParkingToken parkCar(Car car1) throws ParkingLotFullException {
        ParkingLot maxSapceParking = new ParkLotWithMaxSpaceStrategy().getParklotStrategy(this.availableParkingLots);
        if(maxSapceParking.getAvailableSlots() == 0)
        {
            throw new ParkingLotFullException();
        }

        ParkingToken parkingToken =  maxSapceParking.parkCar(car1);
        tokenLotMap.put(parkingToken,maxSapceParking);
        return parkingToken;
    }

    @Override
    public void notifyParkingFull(ParkingLot parkingLot) {
       this.availableParkingLots.remove(parkingLot);
    }

    @Override
    public void notifyParkingHasSpace(ParkingLot parkingLot) {
        this.availableParkingLots.add(parkingLot);
    }

    public ParkingToken parkCarWithStrategy(Car car, ParkingStrategy parkingStrategy) throws ParkingLotFullException{
        if(this.availableParkingLots.size() == 0)
        {
            throw new ParkingLotFullException();
        }

        ParkingLot parkingLot = parkingStrategy.getParklotStrategy(this.availableParkingLots);
        ParkingToken token = parkingLot.parkCar(car);
        tokenLotMap.put(token,parkingLot);
        return token;
    }

    public Car unParkCar(ParkingToken token) throws CarNotFoundException{
        if(!this.tokenLotMap.containsKey(token))
        {
            throw new CarNotFoundException();
        }

        Car car  = this.tokenLotMap.get(token).unparkCar(token);
        this.tokenLotMap.remove(token);
        return car;
    }
}