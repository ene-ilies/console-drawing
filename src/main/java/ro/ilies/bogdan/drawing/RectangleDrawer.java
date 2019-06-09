package ro.ilies.bogdan.drawing;

import ro.ilies.bogdan.geometry.Line;
import ro.ilies.bogdan.geometry.Point;
import ro.ilies.bogdan.geometry.Rectangle;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.*;
import static ro.ilies.bogdan.geometry.Utils.buildLine;

public interface RectangleDrawer extends Consumer<Rectangle> {

    static RectangleDrawer create(Consumer<Pixel> pixelDrawer) {
        return rectangle -> {
            int startX = rectangle.getStart().getX();
            int startY = rectangle.getStart().getY();
            int endX = rectangle.getEnd().getX();
            int endY = rectangle.getEnd().getY();
            if (IntStream.of(startX, startY, endX, endY).anyMatch(x -> x < 0)) {
                throw new IllegalArgumentException("Only first quadrant is supported.");
            }
            Line l1 = buildLine(startX, startY, endX, startY);
            Line l2 = buildLine(startX, startY, startX, endY);
            Line l3 = buildLine(endX, endY, startX, endY);
            Line l4 = buildLine(endX, endY, endX, startY);
            Stream.of(l1, l2, l3, l4).forEach(LineDrawer.create(pixelDrawer));
        };
    }
}
