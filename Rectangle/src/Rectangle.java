/**
 * Created by ankitmishra on 30/11/15.
 */
public class Rectangle {

    private int breadth;
    private int length;


    public Rectangle(int length, int breadth) {
        this.length=length;
        this.breadth = breadth;
    }

    public int getArea() {
        return length*breadth;
    }

    public static Rectangle buildSquare(int length){
        return new Rectangle(length,length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (breadth != rectangle.breadth) return false;
        return length == rectangle.length;

    }

    @Override
    public int hashCode() {
        int result = breadth;
        result = 31 * result + length;
        return result;
    }

    public int getPerimeter() {
        return 2*(this.length+this.breadth);
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }
}
