package ro.ilies.bogdan.command;

import ro.ilies.bogdan.drawing.Canvas;
import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.parser.CreateParser;
import ro.ilies.bogdan.validator.CreateValidator;

import java.util.function.BiConsumer;

public interface CreateCommand extends BiConsumer<String, Canvas> {

    static CreateCommand command() {
        return (params, canvas) -> {
            Size size = CreateParser.parser().apply(params);
            CreateValidator.validator().accept(size);

            canvas.setMatrix(new char[size.getHeight()][size.getWidth()]);
            canvas.setSize(size);
        };
    }
}
