/**
 * Created by ankitmishra on 02/12/15.
 */
public class ParkingToken {
    private String carNumber;

    public ParkingToken(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingToken that = (ParkingToken) o;

        return carNumber.equals(that.carNumber);

    }

    @Override
    public int hashCode() {
        return carNumber.hashCode();
    }
}
