package ro.ilies.bogdan.validator;

import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ValidationException;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public interface CreateValidator extends Consumer<Size> {

    static CreateValidator validator() {
        return size -> {
            IntStream bounds = IntStream.of(size.getWidth(), size.getHeight());
            if (bounds.anyMatch(x -> x < 0 || x > 300)) {
                throw new ValidationException("Bounds out of range: [0..300]");
            }
        };
    }
}
