package subway.domain;

import static subway.domain.init.InitLine.NEW_BUNDANG;
import static subway.domain.init.InitLine.THREE_LINE;
import static subway.domain.init.InitLine.TWO_LINE;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {

    private static final List<Line> lines = List.of(
            Line.withStations(TWO_LINE.getName(), TWO_LINE.getStations()),
            Line.withStations(THREE_LINE.getName(), THREE_LINE.getStations()),
            Line.withStations(NEW_BUNDANG.getName(), NEW_BUNDANG.getStations())
    );

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static void deleteAll() {
        lines.clear();
    }
}
