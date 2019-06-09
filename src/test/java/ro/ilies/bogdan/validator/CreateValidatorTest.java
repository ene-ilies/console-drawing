package ro.ilies.bogdan.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Line;

import static ro.ilies.bogdan.geometry.Utils.buildLine;

public class CreateValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatCreateValidatorDoesNotThrowExceptionWhenSizeInBoundary() {
        Line line = buildLine(1, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatCreateValidatorThrowsExceptionWidthLessThanZero() {
        Size size = Size.builder()
                .width(-1)
                .height(100)
                .build();
        expectedException.expect(ValidationException.class);
        CreateValidator.validator().accept(size);
    }

    @Test
    public void thatCreateValidatorThrowsExceptionWidthGreaterThanLimit() {
        Size size = Size.builder()
                .width(301)
                .height(100)
                .build();
        expectedException.expect(ValidationException.class);
        CreateValidator.validator().accept(size);
    }

    @Test
    public void thatCreateValidatorThrowsExceptionHeightLessThanZero() {
        Size size = Size.builder()
                .width(100)
                .height(-1)
                .build();
        expectedException.expect(ValidationException.class);
        CreateValidator.validator().accept(size);
    }

    @Test
    public void thatCreateValidatorThrowsExceptionHeightGreaterThanLimit() {
        Size size = Size.builder()
                .width(100)
                .height(301)
                .build();
        expectedException.expect(ValidationException.class);
        CreateValidator.validator().accept(size);
    }
}
