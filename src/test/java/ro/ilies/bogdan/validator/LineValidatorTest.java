package ro.ilies.bogdan.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Line;

import static ro.ilies.bogdan.geometry.Utils.buildLine;

public class LineValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatLineValidatorDoesNotThrowExceptionWhenLineInBoundary() {
        Line line = buildLine(1, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionStartXLessThanZero() {
        Line line = buildLine(-1, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionStartXGreaterThanWidth() {
        Line line = buildLine(8, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionEndXLessThanZero() {
        Line line = buildLine(1, 3, -7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionEndXGreaterThanWidth() {
        Line line = buildLine(8, 3, 8, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionStartYLessThanZero() {
        Line line = buildLine(1, -3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionStartYGreaterThanWidth() {
        Line line = buildLine(1, 4, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionEndYLessThanZero() {
        Line line = buildLine(1, 3, 7, -3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatLineValidatorThrowsExceptionEndYGreaterThanWidth() {
        Line line = buildLine(8, 3, 7, 4);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }
}
