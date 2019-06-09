package ro.ilies.bogdan.parser;

import ro.ilies.bogdan.drawing.Size;
import ro.ilies.bogdan.exception.ParseException;

import java.util.function.Function;

public interface CreateParser extends Function<String, Size> {

    static CreateParser parser() {
        return toParse -> {
            String[] params = toParse.split(" ");
            if (params.length != 2) {
                throw new ParseException("Invalid number of params.");
            }
            try {
                return Size.builder().width(Integer.parseInt(params[0]))
                        .height(Integer.parseInt(params[1])).build();
            } catch (NumberFormatException e) {
                throw new ParseException("Parameter is not a number.");
            }
        };
    }
}
