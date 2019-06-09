package ro.ilies.bogdan.drawing;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.geometry.Point;
import ro.ilies.bogdan.geometry.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static ro.ilies.bogdan.geometry.Utils.buildRectangle;

public class RectangleDrawerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatRectangleDrawerCanDrawRectangleLeftToRightTopToBottom() {
        Rectangle rectangle = buildRectangle(1, 1, 3, 3);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 3, 3);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    private List<Pixel> expectedDrawnPixelsForRectangle(int startX, int startY, int endX, int endY) {
        return Stream
                .of(
                        IntStream.rangeClosed(startX, endX).mapToObj(x -> Pixel.builder().x(x).y(startY).build()),
                        IntStream.rangeClosed(startX, endX).mapToObj(x -> Pixel.builder().x(x).y(endY).build()),
                        IntStream.rangeClosed(startY, endY).mapToObj(y -> Pixel.builder().x(startX).y(y).build()),
                        IntStream.rangeClosed(startY, endY).mapToObj(y -> Pixel.builder().x(endX).y(y).build()))
                .flatMap(identity())
                .collect(Collectors.toList());
    }

    @Test
    public void thatRectangleDrawerCanDrawRectangleRightToLeftTopToBottom() {
        Rectangle rectangle = buildRectangle(3, 1, 1, 3);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 3, 3);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatRectangleDrawerCanDrawRectangleLeftToRightBottomToTop() {
        Rectangle rectangle = buildRectangle(1, 3, 3, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 3, 3);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatRectangleDrawerCanDrawRectangleRightToLeftBottomToTop() {
        Rectangle rectangle = buildRectangle(3, 3, 1, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 3, 3);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatRectangleDrawerCanDrawRectangleAsHorizontalLine() {
        Rectangle rectangle = buildRectangle(1, 1, 3, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 3, 1);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatRectangleDrawerCanDrawRectangleAsVerticalLine() {
        Rectangle rectangle = buildRectangle(1, 1, 1, 3);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 1, 3);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatRectangleDrawerCanDrawRectangleAsPoint() {
        Rectangle rectangle = buildRectangle(1, 1, 1, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        rectangleDrawer.accept(rectangle);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForRectangle(1, 1, 1, 1);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeStartX() {
        Rectangle rectangle = buildRectangle(-1, 1, 5, 5);

        Consumer<Pixel> canvas = pixel -> {};
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        expectedException.expect(IllegalArgumentException.class);
        rectangleDrawer.accept(rectangle);
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeEndX() {
        Rectangle rectangle = buildRectangle(1, 1, -1, 5);

        Consumer<Pixel> canvas = pixel -> {};
        Consumer<Rectangle> lineDrawer = RectangleDrawer.create(canvas);
        expectedException.expect(IllegalArgumentException.class);
        lineDrawer.accept(rectangle);
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeStartY() {
        Rectangle rectangle = buildRectangle(1, -1, 5, 5);

        Consumer<Pixel> canvas = pixel -> {};
        Consumer<Rectangle> rectangleDrawer = RectangleDrawer.create(canvas);
        expectedException.expect(IllegalArgumentException.class);
        rectangleDrawer.accept(rectangle);
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeEndY() {
        Rectangle rectangle = buildRectangle(1, 1, 5, -1);

        Consumer<Pixel> canvas = pixel -> {};
        Consumer<Rectangle> rectangleConsumer = RectangleDrawer.create(canvas);
        expectedException.expect(IllegalArgumentException.class);
        rectangleConsumer.accept(rectangle);
    }
}
