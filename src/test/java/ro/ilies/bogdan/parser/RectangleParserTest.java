package ro.ilies.bogdan.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.geometry.Rectangle;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ro.ilies.bogdan.geometry.Utils.buildRectangle;

public class RectangleParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatRectangleParserCreatesALineInstanceFromString() {
        Rectangle rectangle = RectangleParser.parser().apply("1 1 3 3");
        assertThat(rectangle, is(buildRectangle(0, 0, 2, 2)));
    }

    @Test
    public void thatRectangleParserThrowsExceptionWhenLessParams() {
        expectedException.expect(ParseException.class);
        RectangleParser.parser().apply("1 1 3");
    }

    @Test
    public void thatRectangleParserThrowsExceptionWhenMoreParams() {
        expectedException.expect(ParseException.class);
        RectangleParser.parser().apply("1 1 3 3 7");
    }

    @Test
    public void thatRectangleParserThrowsExceptionWhen1stNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        RectangleParser.parser().apply("a 3 7 3");
    }

    @Test
    public void thatRectangleParserThrowsExceptionWhen2ndNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        RectangleParser.parser().apply("1 a 7 3");
    }

    @Test
    public void thatRectangleParserThrowsExceptionWhen3rdNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        RectangleParser.parser().apply("1 3 a 3");
    }

    @Test
    public void thatRectangleParserThrowsExceptionWhen4thNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        RectangleParser.parser().apply("1 3 7 a");
    }
}
