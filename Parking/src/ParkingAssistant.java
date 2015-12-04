import java.util.*;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkingAssistant implements ParkingObserver{
    private List<ParkingComplex> complexes;
    List<ParkingAreaBase> parkingLots;
    List<ParkingAreaBase> availableParkingLots;
    Map<ParkingToken, ParkingAreaBase> tokenLotMap;


    public ParkingAssistant()
    {
        parkingLots = new ArrayList<>();
        tokenLotMap = new HashMap<ParkingToken, ParkingAreaBase>();
    }

    public ParkingAssistant(List<ParkingAreaBase> parkingLots) {
        this.parkingLots=parkingLots;
        this.availableParkingLots = new ArrayList<ParkingAreaBase>();
        this.availableParkingLots.addAll(parkingLots);
        tokenLotMap = new HashMap<ParkingToken, ParkingAreaBase>();
    }

   /* public ParkingAssistant(List<ParkingAreaBase>) {
        this.complexes = parkingComplexes;
        this.parkingLots = new ArrayList<ParkingLot>();
        this.availableParkingLots = new ArrayList<>();

        for (ParkingComplex complex : parkingComplexes) {
            this.parkingLots.addAll(complex.getParkingLots());
            this.availableParkingLots.addAll(complex.getParkingLots());
        }

        if(indiviDualParkingLots!=null && indiviDualParkingLots.size()!=0) {
            this.parkingLots.addAll(indiviDualParkingLots);
            this.availableParkingLots.addAll(indiviDualParkingLots);
        }

    }*/


    public boolean add(ParkingLot pl2) {
        if(this.parkingLots.contains(pl2))
        {
            return false;
        }

        this.parkingLots.add(pl2);
        return true;
    }

    public ParkingToken parkCar(Car car1) throws ParkingLotFullException {
        ParkingAreaBase maxSapceParking = new ParkLotWithMaxSpaceStrategy().getParklotStrategy(this.availableParkingLots);
        if (maxSapceParking.getAvailableSpaces() == 0)
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

        ParkingAreaBase parkingLot = parkingStrategy.getParklotStrategy(this.availableParkingLots);
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