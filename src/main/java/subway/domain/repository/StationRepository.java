package subway.domain.repository;

import static subway.domain.init.InitStation.FOREST;
import static subway.domain.init.InitStation.GANGNAM;
import static subway.domain.init.InitStation.GYODAE;
import static subway.domain.init.InitStation.MAEBONG;
import static subway.domain.init.InitStation.TERMINAL;
import static subway.domain.init.InitStation.YANGJAE;
import static subway.domain.init.InitStation.YEOKSAM;
import static subway.exception.ExceptionMessage.STATION_NOT_FOUND;
import static subway.exception.ExceptionMessage.STATION_SAME_EXCEPTION;

import subway.domain.Station;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {

    private static final List<Station> stations = List.of(
            Station.from(GYODAE.getName()),
            Station.from(GANGNAM.getName()),
            Station.from(YEOKSAM.getName()),
            Station.from(TERMINAL.getName()),
            Station.from(YANGJAE.getName()),
            Station.from(FOREST.getName()),
            Station.from(MAEBONG.getName())
    );

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }

    public static void validateIsBothNotSame(final Station start, final Station end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException(STATION_SAME_EXCEPTION.getMessage());
        }
    }

    public static Station findByName(final String name) {
        return stations().stream()
                .filter(station -> station.isSame(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(STATION_NOT_FOUND.getMessage()));
    }
}
