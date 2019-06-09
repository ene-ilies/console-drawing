package ro.ilies.bogdan.command;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.drawing.Pixel;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.exception.UninitializedCanvasException;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Rectangle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class RectangleCommandTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatRectangleCommandDrawsRectangleProperly() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        canvas.setSize(Size.builder().width(5).height(5).build());

        RectangleCommand.command().accept("1 1 3 3", canvas);

        char[][] expectedCanvas = expectedCanvas(0, 0, 2, 2);
        assertThat(canvas.getMatrix(), equalTo(expectedCanvas));
    }

    private char[][] expectedCanvas(int startX, int startY, int endX, int endY) {
        char[][] matrix = new char[5][5];
        Stream
                .of(
                        IntStream.rangeClosed(startX, endX).mapToObj(x -> Pixel.builder().x(x).y(startY).build()),
                        IntStream.rangeClosed(startX, endX).mapToObj(x -> Pixel.builder().x(x).y(endY).build()),
                        IntStream.rangeClosed(startY, endY).mapToObj(y -> Pixel.builder().x(startX).y(y).build()),
                        IntStream.rangeClosed(startY, endY).mapToObj(y -> Pixel.builder().x(endX).y(y).build()))
                .flatMap(identity())
                .forEach(pixel -> matrix[pixel.getY()][pixel.getX()] = 'x');
        return matrix;
    }

    @Test
    public void thatRectangleCommandThrowsExceptionWhenCanvasMatrixNotInitialized() {
        Canvas canvas = new Canvas();
        canvas.setSize(Size.builder().width(5).height(5).build());
        expectedException.expect(UninitializedCanvasException.class);
        RectangleCommand.command().accept("1 1 3 3", canvas);
    }

    @Test
    public void thatRectangleCommandThrowsExceptionWhenCanvasSizeNotInitialized() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        expectedException.expect(UninitializedCanvasException.class);
        RectangleCommand.command().accept("1 1 3 3", canvas);
    }

    @Test
    public void thatRectangleCommandThrowsExceptionWhenUnableToParse() {
        Canvas canvas = new Canvas();
        canvas.setSize(Size.builder().width(5).height(5).build());
        canvas.setMatrix(new char[5][5]);
        expectedException.expect(ParseException.class);
        RectangleCommand.command().accept("1 1 a 3", canvas);
    }

    @Test
    public void thatRectangleCommandThrowsExceptionWhenGivenSizeIsNotValid() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        canvas.setSize(Size.builder().width(5).height(5).build());
        expectedException.expect(ValidationException.class);
        RectangleCommand.command().accept("1 1 6 6", canvas);
    }
}
