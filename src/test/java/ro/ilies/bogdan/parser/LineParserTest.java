package ro.ilies.bogdan.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.geometry.Line;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ro.ilies.bogdan.geometry.Utils.buildLine;

public class LineParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatLineParserCreatesALineInstanceFromString() {
        Line line = LineParser.parser().apply("1 3 7 3");
        assertThat(line, is(buildLine(0, 2, 6, 2)));
    }

    @Test
    public void thatLineParserThrowsExceptionWhenLessParams() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 3 7");
    }

    @Test
    public void thatLineParserThrowsExceptionWhenMoreParams() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 3 7 3 5");
    }

    @Test
    public void thatLineParserThrowsExceptionWhen1stNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("a 3 7 3");
    }

    @Test
    public void thatLineParserThrowsExceptionWhen2ndNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 a 7 3");
    }

    @Test
    public void thatLineParserThrowsExceptionWhen3rdNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 3 a 3");
    }

    @Test
    public void thatLineParserThrowsExceptionWhen4thNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 3 7 a");
    }
}
