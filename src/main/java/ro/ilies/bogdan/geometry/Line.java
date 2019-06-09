package ro.ilies.bogdan.geometry;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Line {
    Point start;
    Point end;
}
