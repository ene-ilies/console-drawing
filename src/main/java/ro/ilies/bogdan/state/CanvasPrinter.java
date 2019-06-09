package ro.ilies.bogdan.state;

import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.UninitializedCanvasException;

import java.util.Optional;
import java.util.function.Consumer;

public interface CanvasPrinter extends Consumer<Canvas> {

    static CanvasPrinter printer() {
        return canvas -> {
            Size size = Optional.ofNullable(canvas.getSize())
                    .orElseThrow(() -> new UninitializedCanvasException("Canvas not initialized"));
            char[][] matrix = Optional.ofNullable(canvas.getMatrix())
                    .orElseThrow(() -> new UninitializedCanvasException("Canvas not initialized"));
            for (int i = 0; i < size.getWidth() + 2; i++) {
                System.out.print("-");
            }
            System.out.println();

            for (int i = 0; i < size.getHeight(); i++) {
                System.out.print("|");
                for (int j = 0; j < size.getWidth(); j++) {
                    char value = (matrix[i][j] == 0)? ' ' : matrix[i][j];
                    System.out.print(value);
                }
                System.out.println("|");
            }

            for (int i = 0; i < size.getWidth() + 2; i++) {
                System.out.print("-");
            }
            System.out.println();
        };
    }
}
