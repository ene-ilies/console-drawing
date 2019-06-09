package ro.ilies.bogdan.drawing;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Line;
import ro.ilies.bogdan.geometry.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static ro.ilies.bogdan.geometry.Utils.buildLine;

public class LineDrawerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatLineDrawerCanDrawVerticalLineTopToDown() {
        Line line = buildLine(1, 1, 1, 5);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        lineDrawer.accept(line);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForVerticalLine(1, 1, 5);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    private List<Pixel> expectedDrawnPixelsForVerticalLine(int x, int y1, int y2) {
        return IntStream.rangeClosed(y1, y2)
                .mapToObj(y -> Pixel.builder().x(x).y(y).build())
                .collect(Collectors.toList());
    }

    @Test
    public void thatLineDrawerCanDrawVerticalLineDownToTop() {
        Line line = buildLine(1, 5, 1, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        lineDrawer.accept(line);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForVerticalLine(1, 1, 5);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatLineDrawerCanDrawHorizontalLineLeftToRight() {
        Line line = buildLine(1, 1, 5, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        lineDrawer.accept(line);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForHorizontalLine(1, 1, 5);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    private List<Pixel> expectedDrawnPixelsForHorizontalLine(int y, int x1, int x2) {
        return IntStream.rangeClosed(x1, x2)
                .mapToObj(x -> Pixel.builder().x(x).y(y).build())
                .collect(Collectors.toList());
    }

    @Test
    public void thatLineDrawerCanDrawHorizontalLineRightToLeft() {
        Line line = buildLine(5, 1, 1, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        lineDrawer.accept(line);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForHorizontalLine(1, 1, 5);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatLineDrawerCanDrawLineAsPoint() {
        Line line = buildLine(1, 1, 1, 1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        lineDrawer.accept(line);
        List<Pixel> expectedDrawnPixels = expectedDrawnPixelsForHorizontalLine(1, 1, 1);
        assertThat(collectedDrawnPixels, containsInAnyOrder(expectedDrawnPixels.toArray()));
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeStartX() {
        Line line = buildLine(-1, 1, -1, 5);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        expectedException.expect(ValidationException.class);
        lineDrawer.accept(line);
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeEndX() {
        Line line = buildLine(-1, 1, -1, 5);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        expectedException.expect(ValidationException.class);
        lineDrawer.accept(line);
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeStartY() {
        Line line = buildLine(1, -1, 1, 5);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        expectedException.expect(ValidationException.class);
        lineDrawer.accept(line);
    }

    @Test
    public void thatLineDrawerDoesNotSupportNegativeEndY() {
        Line line = buildLine(1, 1, 1, -1);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        expectedException.expect(ValidationException.class);
        lineDrawer.accept(line);
    }

    @Test
    public void thatLineDrawerDoesNotSupportLineInAngle() {
        Line line = buildLine(1, 1, 5, 5);

        List<Pixel> collectedDrawnPixels = new LinkedList<>();
        Consumer<Pixel> canvas = collectedDrawnPixels::add;
        Consumer<Line> lineDrawer = LineDrawer.create(canvas);
        expectedException.expect(ValidationException.class);
        lineDrawer.accept(line);
    }
}
