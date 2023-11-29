package subway.domain;

import java.util.Objects;

public class Station {

    private String name;

    public Station(String name) {
        this.name = name;
    }

    public static Station from(final String name) {
        return new Station(name);
    }

    public boolean isSame(final String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Station station = (Station) other;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
