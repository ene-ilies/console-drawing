package ro.ilies.bogdan.validator;

import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Rectangle;

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public interface RectangleValidator extends BiConsumer<Rectangle, Size> {

    static RectangleValidator validator() {
        return (rectangle, size) -> {
            boolean horizontalOutsider = IntStream.of(rectangle.getStart().getX(), rectangle.getEnd().getX())
                    .anyMatch(x -> x < 0 || x >= size.getWidth() );
            boolean verticalOutsider = IntStream.of(rectangle.getStart().getY(), rectangle.getEnd().getY())
                    .anyMatch(x -> x < 0 || x >= size.getHeight());
            if (horizontalOutsider || verticalOutsider) {
                throw new ValidationException("Out of drawing area.");
            }
        };
    }

}
