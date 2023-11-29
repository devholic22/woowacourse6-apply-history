package subway.domain.init;

import static subway.domain.init.InitStation.FOREST;
import static subway.domain.init.InitStation.GANGNAM;
import static subway.domain.init.InitStation.GYODAE;
import static subway.domain.init.InitStation.MAEBONG;
import static subway.domain.init.InitStation.TERMINAL;
import static subway.domain.init.InitStation.YANGJAE;
import static subway.domain.init.InitStation.YEOKSAM;

import java.util.List;

public enum InitLine {

    TWO_LINE("2호선", List.of(GYODAE.getName(), GANGNAM.getName(), YEOKSAM.getName())),
    THREE_LINE("3호선", List.of(GYODAE.getName(), TERMINAL.getName(), YANGJAE.getName(), MAEBONG.getName())),
    NEW_BUNDANG("신분당선", List.of(GANGNAM.getName(), YANGJAE.getName(), FOREST.getName()));

    private final String name;
    private final List<String> stations;

    InitLine(final String name, final List<String> stations) {
        this.name = name;
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public List<String> getStations() {
        return stations;
    }
}
