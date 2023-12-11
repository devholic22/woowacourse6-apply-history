package subway.domain.init;

public enum InitStation {

    GYODAE("교대역"),
    GANGNAM("강남역"),
    YEOKSAM("역삼역"),
    TERMINAL("남부터미널역"),
    YANGJAE("양재역"),
    FOREST("양재시민의숲역"),
    MAEBONG("매봉역");

    private final String name;

    InitStation(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
