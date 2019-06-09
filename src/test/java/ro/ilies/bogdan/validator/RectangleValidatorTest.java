package ro.ilies.bogdan.validator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Line;
import ro.ilies.bogdan.geometry.Rectangle;

import static ro.ilies.bogdan.geometry.Utils.buildLine;
import static ro.ilies.bogdan.geometry.Utils.buildRectangle;

public class RectangleValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void thatRectangleValidatorDoesNotThrowExceptionWhenRectangleInBoundary() {
        Rectangle rectangle = buildRectangle(1, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionStartXLessThanZero() {
        Line line = buildLine(-1, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        LineValidator.validator().accept(line, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionStartXGreaterThanWidth() {
        Rectangle rectangle = buildRectangle(8, 3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionEndXLessThanZero() {
        Rectangle rectangle = buildRectangle(1, 3, -7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionEndXGreaterThanWidth() {
        Rectangle rectangle = buildRectangle(8, 3, 8, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionStartYLessThanZero() {
        Rectangle rectangle = buildRectangle(1, -3, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionStartYGreaterThanWidth() {
        Rectangle rectangle = buildRectangle(1, 4, 7, 3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionEndYLessThanZero() {
        Rectangle rectangle = buildRectangle(1, 3, 7, -3);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }

    @Test
    public void thatRectangleValidatorThrowsExceptionEndYGreaterThanWidth() {
        Rectangle rectangle = buildRectangle(8, 3, 7, 4);
        Size size = Size.builder()
                .width(8)
                .height(4)
                .build();
        expectedException.expect(ValidationException.class);
        RectangleValidator.validator().accept(rectangle, size);
    }
}
