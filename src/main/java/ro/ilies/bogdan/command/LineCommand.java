package ro.ilies.bogdan.command;

import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.state.CanvasWriter;
import ro.ilies.bogdan.drawing.LineDrawer;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.UninitializedCanvasException;
import ro.ilies.bogdan.geometry.Line;
import ro.ilies.bogdan.parser.LineParser;
import ro.ilies.bogdan.validator.LineValidator;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface LineCommand extends BiConsumer<String, Canvas> {

    static LineCommand command() {
        return (params, canvas) -> {
            Line line = LineParser.parser().apply(params);
            Size size = Optional.ofNullable(canvas.getSize())
                    .orElseThrow(() -> new UninitializedCanvasException("Canvas not initialized"));
            LineValidator.validator().accept(line, size);
            LineDrawer.create(CanvasWriter.writer(canvas)).accept(line);
        };
    }
}
