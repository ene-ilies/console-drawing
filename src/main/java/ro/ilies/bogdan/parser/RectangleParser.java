package ro.ilies.bogdan.parser;

import ro.ilies.bogdan.exception.ParseException;
import ro.ilies.bogdan.geometry.Rectangle;
import ro.ilies.bogdan.geometry.Utils;

import java.util.function.Function;

public interface RectangleParser extends Function<String, Rectangle> {

    static RectangleParser parser() {
        return toParse -> {
            String[] params = toParse.split(" ");
            if (params.length != 4) {
                throw new ParseException("Invalid number of params.");
            }
            try {
                return Utils.buildRectangle(Integer.parseInt(params[0]) - 1, Integer.parseInt(params[1]) - 1,
                        Integer.parseInt(params[2]) - 1, Integer.parseInt(params[3]) - 1);
            } catch (NumberFormatException e) {
                throw new ParseException("Parameter is not a number.");
            }
        };
    }
}
