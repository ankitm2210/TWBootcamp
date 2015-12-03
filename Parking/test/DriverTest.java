import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;

/**
 * Created by ankitmishra onß 02/12/15.
 */
public class DriverTest {

    @Test
    public void ParkingShouldBeAvailable() throws Exception {
        ParkingLot parkinglot = new ParkingLot(10);
        Assert.assertTrue(parkinglot.IsParkingAvailable());
    }

    @Test
    public void ShouldBeAbleParkTheCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("A");
        Assert.assertNotNull(parkingLot.parkCar(car));
    }

    @Test
    public void ShouldNotBeAbleToParkTheSameCarTwice() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("B");
        Assert.assertNotNull(parkingLot.parkCar(car));
        Assert.assertNull(parkingLot.parkCar(car));
    }

    @Test
    public void ShouldBeAbleToUnparkHisCar() throws Exception
    {
        ParkingLot parkingLot = new ParkingLot(10);
        String carNumber = "B";
        Car car = new Car(carNumber);
        ParkingToken token = parkingLot.parkCar(car);
        Assert.assertEquals(car,parkingLot.unparkCar(token));
    }


    @Test(expected=CarNotFoundException.class)
    public void ShouldThrowCardNotFoundException() throws Exception
    {
        ParkingLot parkingLot = new ParkingLot(10);
        String carNumber = "B";
        Car car = new Car(carNumber);
        ParkingToken token = new ParkingToken("45445");
        Assert.assertEquals(car,parkingLot.unparkCar(token));
    }

    @Test
    public void ShouldNotifyIfParkingLotIsFull() throws Exception {
        ParkingObserver manager = Mockito.mock(ParkingManager.class);
        ParkingLot parkingLot = new ParkingLot(50);
        parkingLot.attachObserver(manager);
        for(int i=0;i<=48;i++)
        {
            Car car = new Car("A" + i);
            parkingLot.parkCar(car);
        }

        parkingLot.parkCar(new Car("A" + 49));
        Mockito.verify(manager, Mockito.atLeastOnce()).notifyParkingFull();
    }

    @Test
    public void ShouldNotifyAllObserversIfParkingLotIsFull() throws Exception {
        ParkingObserver manager = Mockito.mock(ParkingObserver.class);
        ParkingObserver securityPersonel = Mockito.mock(ParkingObserver.class);
        ParkingLot parkingLot = new ParkingLot(50);
        parkingLot.attachObserver(manager);
        parkingLot.attachObserver(securityPersonel);
        for(int i=0;i<=48;i++)
        {
            Car car = new Car("A" + i);
            parkingLot.parkCar(car);
        }

        parkingLot.parkCar(new Car("A" + 49));
        Mockito.verify(manager).notifyParkingFull();
        Mockito.verify(securityPersonel, Mockito.atLeastOnce()).notifyParkingFull();
        parkingLot.detachObserver(manager);
        parkingLot.detachObserver(securityPersonel);
    }

    @Test
    public void ShouldNotifyIfFullParkingLotHasSpaceAgainIfCarisUnparked() throws Exception {
        ParkingObserver manager = Mockito.mock(ParkingObserver.class);
        ParkingLot parkingLot = new ParkingLot(50);
        parkingLot.attachObserver(manager);
        for(int i=0;i<=48;i++)
        {
            Car car = new Car("A" + i);
            parkingLot.parkCar(car);
        }

        Car lastCar = new Car("A" + 49);
        parkingLot.unparkCar(parkingLot.parkCar(lastCar));

        Mockito.verify(manager).notifyParkingHasSpace();
        parkingLot.detachObserver(manager);
    }
}