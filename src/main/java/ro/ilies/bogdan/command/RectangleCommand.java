package ro.ilies.bogdan.command;

import ro.ilies.bogdan.drawing.*;
import ro.ilies.bogdan.exception.UninitializedCanvasException;
import ro.ilies.bogdan.geometry.Rectangle;
import ro.ilies.bogdan.parser.RectangleParser;
import ro.ilies.bogdan.state.CanvasWriter;
import ro.ilies.bogdan.validator.RectangleValidator;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface RectangleCommand extends BiConsumer<String, Canvas> {

    static RectangleCommand command() {
        return (params, canvas) -> {
            Rectangle rectangle = RectangleParser.parser().apply(params);
            Size size = Optional.ofNullable(canvas.getSize())
                    .orElseThrow(() -> new UninitializedCanvasException("Canvas not initialized"));
            RectangleValidator.validator().accept(rectangle, size);
            RectangleDrawer.create(CanvasWriter.writer(canvas)).accept(rectangle);
        };
    }
}
