package ro.ilies.bogdan.state;

import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.drawing.Pixel;
import ro.ilies.bogdan.exception.UninitializedCanvasException;

import java.util.Optional;
import java.util.function.Consumer;

public interface CanvasWriter extends Consumer<Pixel> {

    static CanvasWriter writer(Canvas canvas) {
        char[][] matrix = Optional.ofNullable(canvas.getMatrix())
                .orElseThrow(() -> new UninitializedCanvasException("Canvas not initialized"));
        return pixel -> matrix[pixel.getY()][pixel.getX()] = 'x';
    }
}
