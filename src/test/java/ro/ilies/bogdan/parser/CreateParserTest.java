package ro.ilies.bogdan.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ParseException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CreateParserTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatCreateParserCreatesASizeInstanceFromString() {
        Size size = CreateParser.parser().apply("3 5");
        assertThat(size, is(Size.builder().width(3).height(5).build()));
    }

    @Test
    public void thatCreateParserThrowsExceptionWhenLessParams() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("3");
    }

    @Test
    public void thatCreateParserThrowsExceptionWhenMoreParams() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 3 3");
    }

    @Test
    public void thatCreateParserThrowsExceptionWhen1stNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("a 3 7 3");
    }

    @Test
    public void thatCreateParserThrowsExceptionWhen2ndNumberIsNotANumber() {
        expectedException.expect(ParseException.class);
        LineParser.parser().apply("1 a 7 3");
    }
}
