package subway.domain.repository;

import static subway.exception.ExceptionMessage.STATION_NOT_FOUND;
import static subway.exception.ExceptionMessage.STATION_SAME_EXCEPTION;

import subway.domain.Station;
import subway.domain.init.InitStation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StationRepository {

    private static final List<Station> stations = Arrays.stream(InitStation.values())
            .map(station -> Station.from(station.getName()))
            .collect(Collectors.toList());

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
