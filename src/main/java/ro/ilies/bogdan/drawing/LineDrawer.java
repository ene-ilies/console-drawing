package ro.ilies.bogdan.drawing;

import ro.ilies.bogdan.exception.ValidationException;
import ro.ilies.bogdan.geometry.Line;

import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public interface LineDrawer extends Consumer<Line> {

    static LineDrawer create(Consumer<Pixel> pixelDrawer) {
        return line -> {
            int startX = line.getStart().getX();
            int startY = line.getStart().getY();
            int endX = line.getEnd().getX();
            int endY = line.getEnd().getY();
            if (IntStream.of(startX, startY, endX, endY).anyMatch(x -> x < 0)) {
                throw new ValidationException("Only first quadrant is supported.");
            }
            int deltaX = abs(abs(startX) - abs(endX));
            int deltaY = abs(abs(startY) - abs(endY));
            if (deltaX == 0) {
                int minY = min(startY, endY);
                int maxY = max(startY, endY);
                IntStream.rangeClosed(minY, maxY)
                        .mapToObj(y -> Pixel.builder()
                                .x(startX)
                                .y(y)
                                .build())
                        .forEach(pixelDrawer);
            } else if (deltaY == 0) {
                int minX = min(startX, endX);
                int maxX = max(startX, endX);
                IntStream.rangeClosed(minX, maxX)
                        .mapToObj(x -> Pixel.builder()
                                .x(x)
                                .y(startY)
                                .build())
                        .forEach(pixelDrawer);
            } else {
                throw new ValidationException("Only horizontal or vertical line is supported.");
            }
        };
    }
}
