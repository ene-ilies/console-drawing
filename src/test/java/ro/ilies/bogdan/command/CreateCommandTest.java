package ro.ilies.bogdan.command;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.exception.ValidationException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CreateCommandTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatCreateCommandInitializesCanvasProperly() {
        Canvas canvas = new Canvas();

        CreateCommand.command().accept("3 5", canvas);

        assertThat(canvas.getSize(), is(Size.builder().width(3).height(5).build()));
        assertThat(canvas.getMatrix(), equalTo(new char[5][3]));
    }

    @Test
    public void thatCreateCommandThrowsExceptionWhenUnableToParse() {
        Canvas canvas = new Canvas();
        expectedException.expect(ParseException.class);
        CreateCommand.command().accept("3 a", canvas);
    }

    @Test
    public void thatCreateCommandThrowsExceptionWhenGivenSizeIsNotValid() {
        Canvas canvas = new Canvas();
        expectedException.expect(ValidationException.class);
        CreateCommand.command().accept("301 300", canvas);
    }
}
