package subway.domain.repository;

import subway.domain.Line;
import subway.domain.init.InitLine;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LineRepository {

    private static final List<Line> lines = Arrays.stream(InitLine.values())
            .map(line -> Line.withStations(line.getName(), line.getStations()))
            .collect(Collectors.toList());

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
