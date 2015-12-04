import java.util.List;

/**
 * Created by ankitmishra on 03/12/15.
 */
public interface ParkingStrategy {
    ParkingAreaBase getParklotStrategy(List<ParkingAreaBase> availableParkingLots);
}
