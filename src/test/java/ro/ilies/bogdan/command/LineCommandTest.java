package ro.ilies.bogdan.command;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.exception.UninitializedCanvasException;
import ro.ilies.bogdan.exception.ValidationException;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LineCommandTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatLineCommandDrawsLineProperly() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        canvas.setSize(Size.builder().width(5).height(5).build());

        LineCommand.command().accept("1 3 4 3", canvas);

        char[][] expectedCanvas = expectedCanvas(2, 0, 3);
        assertThat(canvas.getMatrix(), equalTo(expectedCanvas));
    }

    private char[][] expectedCanvas(int y, int x1, int x2) {
        char[][] matrix = new char[5][5];
        IntStream.rangeClosed(x1, x2).forEach(x -> matrix[y][x] = 'x');
        return matrix;
    }

    @Test
    public void thatCreateCommandThrowsExceptionWhenCanvasMatrixNotInitialized() {
        Canvas canvas = new Canvas();
        canvas.setSize(Size.builder().width(5).height(5).build());
        expectedException.expect(UninitializedCanvasException.class);
        LineCommand.command().accept("1 3 4 3", canvas);
    }

    @Test
    public void thatCreateCommandThrowsExceptionWhenCanvasSizeNotInitialized() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        expectedException.expect(UninitializedCanvasException.class);
        LineCommand.command().accept("1 3 4 3", canvas);
    }

    @Test
    public void thatCreateCommandThrowsExceptionWhenUnableToParse() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        canvas.setSize(Size.builder().width(5).height(5).build());
        expectedException.expect(ParseException.class);
        LineCommand.command().accept("1 3 a 3", canvas);
    }

    @Test
    public void thatCreateCommandThrowsExceptionWhenGivenSizeIsNotValid() {
        Canvas canvas = new Canvas();
        canvas.setMatrix(new char[5][5]);
        canvas.setSize(Size.builder().width(5).height(5).build());
        expectedException.expect(ValidationException.class);
        LineCommand.command().accept("1 6 4 6", canvas);
    }
}
