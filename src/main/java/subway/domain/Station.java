package subway.domain;

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
}
