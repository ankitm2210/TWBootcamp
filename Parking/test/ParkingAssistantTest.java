import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public class ParkingAssistantTest {
    @Test
    public void testParkingLotsShouldAddaNewParkingLot() throws Exception {
        ParkingLot pl1= new ParkingLot(5, 2.0);
        ParkingAssistant lot = new ParkingAssistant();
        Assert.assertTrue(lot.add(pl1));
    }

    @Test
    public void ShouldNotAddSameParkingLotTwice() throws Exception {
        ParkingLot pl1= new ParkingLot(5, 2.0);
        ParkingAssistant lot = new ParkingAssistant();
        Assert.assertTrue(lot.add(pl1));
        Assert.assertFalse(lot.add(pl1));
    }

    @Test
    public void LotshouldBeAbleToParkACarWithMaxSpace() throws Exception {
        ParkingLot pl1= new ParkingLot(5, 2.0);
        ParkingLot pl2= new ParkingLot(6, 2.0);

        List<ParkingAreaBase> plots = new ArrayList<ParkingAreaBase>();
        plots.add(pl1);
        plots.add(pl2);
        Car car1 = new Car("car1");
        ParkingLotOwner owner = new ParkingLotOwner(plots);
        ParkingAssistant assistant = new ParkingAssistant(owner.getParkingLots());
        ParkingToken token = assistant.parkCar(car1);
        Assert.assertEquals(5, pl2.getAvailableSpaces());
        Assert.assertTrue(pl2.isCarAlreadyParked(car1));
    }

    @Test
    public void ParkingAttentendShouldParkTheCarInTheNearestParkingLotForHandicapped() throws Exception {
        ParkingLot pl1 = new ParkingLot(2, 1.0);
        ParkingLot pl2 = new ParkingLot(3, 2.0);
        ParkingLot pl3 = new ParkingLot(1, 1.5);

        List<ParkingAreaBase> plots = new ArrayList<ParkingAreaBase>();
        plots.add(pl1);
        plots.add(pl2);
        plots.add(pl3);

        ParkingLotOwner owner = new ParkingLotOwner(plots);
        ParkingAssistant assistant = new ParkingAssistant(owner.getParkingLots());
        pl1.attachObserver(assistant);
        pl2.attachObserver(assistant);
        pl3.attachObserver(assistant);

        Car car1 = new Car("car1");
        ParkingToken token = assistant.parkCarWithStrategy(car1, new ParkNearestStrategy());
        Assert.assertEquals(car1, pl1.unparkCar(token));
    }

    @Test
    public void parkingAssistantSHouldParkTheCarInCheapestLot() throws Exception {
        ParkingLot pl1 = new ParkingLot(2, 1.0,10.0);
        ParkingLot pl2 = new ParkingLot(3, 2.0, 15.0);
        ParkingLot pl3 = new ParkingLot(1, 1.5, 20.0);

        List<ParkingAreaBase> plots = new ArrayList<ParkingAreaBase>();
        plots.add(pl1);
        plots.add(pl2);
        plots.add(pl3);

        ParkingLotOwner owner = new ParkingLotOwner(plots);
        ParkingAssistant assistant = new ParkingAssistant(owner.getParkingLots());
        pl1.attachObserver(assistant);
        pl2.attachObserver(assistant);
        pl3.attachObserver(assistant);

        Car car = new Car("Car1");
        ParkingToken token = assistant.parkCarWithStrategy(car, new ParkCheapestStrategy());
        Assert.assertEquals(car, pl1.unparkCar(token));

    }

    @Test
    public void ParkingAssistantShouldBeAbleToUnpark() throws Exception {
        ParkingLot pl1 = new ParkingLot(2, 1.0,10.0);
        List<ParkingAreaBase> plots = new ArrayList<ParkingAreaBase>();
        plots.add(pl1);
        ParkingLotOwner owner = new ParkingLotOwner(plots);
        ParkingAssistant assistant = new ParkingAssistant(owner.getParkingLots());
        pl1.attachObserver(assistant);
        Car car = new Car("car1");
        ParkingToken token = assistant.parkCar(car);
        Assert.assertEquals(car,assistant.unParkCar(token));
    }

    @Test
    public void ParkingAssistantShouldBeAbleToParkInParkingComplex() throws Exception {
        ParkingLot parkingLotInComplex = new ParkingLot(2);
        List<ParkingAreaBase> plots = new ArrayList<ParkingAreaBase>();
        plots.add(parkingLotInComplex);
        ParkingComplex complex = new ParkingComplex(plots, 10, 10);

        List<ParkingAreaBase> parkingComplexes = new ArrayList<>();
        parkingComplexes.add(complex);
        ParkingAssistant assistant = new ParkingAssistant(parkingComplexes);
        Car car = new Car("car1");
        ParkingToken token = assistant.parkCar(car);
        Assert.assertEquals(car, parkingLotInComplex.unparkCar(token));
    }


    @Test
    public void ParkingAssistantShouldBeAbleToParkInIndividualParking() throws Exception {
        ParkingLot parkingLotInComplex = new ParkingLot(2);
        List<ParkingAreaBase> plotsInComplex = new ArrayList<ParkingAreaBase>();
        plotsInComplex.add(parkingLotInComplex);
        ParkingComplex complex = new ParkingComplex(plotsInComplex, 10, 10);


        ParkingAreaBase individualParkingLot = new ParkingLot(10, 1.0, 10.0);

        List<ParkingAreaBase> parkingAreas = new ArrayList<ParkingAreaBase>();
        parkingAreas.add(individualParkingLot);
        parkingAreas.add(complex);

        ParkingAssistant assistant = new ParkingAssistant(parkingAreas);
        Car car = new Car("car1");
        ParkingToken token = assistant.parkCar(car);
        Assert.assertEquals(car, individualParkingLot.unparkCar(token));
    }

    @Test
    public void ParkingComplexShouldBeAbleToUnparkAParkedCar() throws Exception {
        ParkingLot parkingLotInComplex = new ParkingLot(2);
        List<ParkingAreaBase> plots = new ArrayList<ParkingAreaBase>();
        plots.add(parkingLotInComplex);
        ParkingComplex complex = new ParkingComplex(plots, 10, 10);

        List<ParkingAreaBase> parkingComplexes = new ArrayList<>();
        parkingComplexes.add(complex);
        ParkingAssistant assistant = new ParkingAssistant(parkingComplexes);
        Car car = new Car("car1");
        ParkingToken token = assistant.parkCar(car);
        Assert.assertEquals(car, complex.unparkCar(token));
    }
}
