package subway.domain.init;

import static subway.domain.init.InitStation.FOREST;
import static subway.domain.init.InitStation.GANGNAM;
import static subway.domain.init.InitStation.GYODAE;
import static subway.domain.init.InitStation.MAEBONG;
import static subway.domain.init.InitStation.TERMINAL;
import static subway.domain.init.InitStation.YANGJAE;
import static subway.domain.init.InitStation.YEOKSAM;

public enum InitSection {

    GYODAE_TO_GANGNAM(GYODAE.getName(), GANGNAM.getName(), 2, 3),
    GANGNAM_TO_YEOKSAM(GANGNAM.getName(), YEOKSAM.getName(), 2, 3),
    GYODAE_TO_TERMINAL(GYODAE.getName(), TERMINAL.getName(), 3, 2),
    TERMINAL_TO_YANGJAE(TERMINAL.getName(), YANGJAE.getName(), 6, 5),
    YANGJAE_TO_MAEBONG(YANGJAE.getName(), MAEBONG.getName(), 1, 1),
    GANGNAM_TO_YANGJAE(GANGNAM.getName(), YANGJAE.getName(), 2, 8),
    YANGJAE_TO_FOREST(YANGJAE.getName(), FOREST.getName(), 10, 3);

    private final String start;
    private final String end;
    private final int distance;
    private final int time;

    InitSection(final String start, final String end, final int distance, final int time) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }
}
