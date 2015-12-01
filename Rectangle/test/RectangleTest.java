
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.css.Rect;

/**
 * Created by ankitmishra on 30/11/15.
 */
public class RectangleTest {

    @Test
    public void shouldCreateNewRectangleWithGivenLengthAndBreadth() throws Exception {
        Rectangle testRectangle = new Rectangle(5,10);
        Assert.assertEquals(5,testRectangle.getLength());
        Assert.assertEquals(10,testRectangle.getBreadth());
    }

    @Test
    public void shouldBeequaltoAnotherRectangleWithSameParameters() throws Exception {
        Rectangle r1 = new Rectangle(5,10);
        Rectangle r2 = new Rectangle(5,10);
        Assert.assertEquals(r1,r2);
    }

    @Test
    public void shouldCalculateAreaOfTheRectangle() throws Exception {
        Rectangle testRectangle = new Rectangle(5,10);
        Assert.assertEquals(50,testRectangle.getArea());
    }

    @Test
    public void shouldCalculatePerimeterOfTheRectangle() throws Exception {
        Rectangle rectangle = new Rectangle(5,10);
        Assert.assertEquals(30,rectangle.getPerimeter());
    }

    public void shouldBuildaSquareEqualToARectangle()
    {
        Rectangle square = Rectangle.buildSquare(10);
        Rectangle rectangle = new Rectangle(10,10);
        Assert.assertEquals(square,rectangle);
    }
}
