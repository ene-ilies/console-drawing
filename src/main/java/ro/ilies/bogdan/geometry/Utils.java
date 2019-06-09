package ro.ilies.bogdan.geometry;

public class Utils {

    private Utils() {
    }

    public static Line buildLine(int startX, int startY, int endX, int endY) {
        return Line.builder()
                .start(Point.builder()
                        .x(startX)
                        .y(startY)
                        .build())
                .end(Point.builder()
                        .x(endX)
                        .y(endY)
                        .build())
                .build();
    }

    public static Rectangle buildRectangle(int startX, int startY, int endX, int endY) {
        return Rectangle.builder()
                .start(Point.builder().x(startX).y(startY).build())
                .end(Point.builder().x(endX).y(endY).build())
                .build();
    }

}
