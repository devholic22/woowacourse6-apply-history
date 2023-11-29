package subway.domain;

import java.util.List;

public class Line {

    private final String name;
    private final List<Station> stations;

    public Line(final String name, final List<Station> stations) {
        this.name = name;
        this.stations = stations;
    }

    public static Line withStations(final String name, final List<Station> stations) {
        return new Line(name, stations);
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
    public List<Station> getStations() {
        return stations;
    }
}
