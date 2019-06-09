package ro.ilies.bogdan.validator;

import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Line;

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public interface LineValidator extends BiConsumer<Line, Size> {

    static LineValidator validator() {
        return (line, size) -> {
            boolean horizontalOutsider = IntStream.of(line.getStart().getX(), line.getEnd().getX())
                    .anyMatch(x -> x < 0 || x >= size.getWidth() );
            boolean verticalOutsider = IntStream.of(line.getStart().getY(), line.getEnd().getY())
                    .anyMatch(x -> x < 0 || x >= size.getHeight());
            if (horizontalOutsider || verticalOutsider) {
                throw new ValidationException("Out of drawing area.");
            }
        };
    }
}
