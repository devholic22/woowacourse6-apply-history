package subway.domain.repository;

import static subway.domain.init.InitSection.GANGNAM_TO_YANGJAE;
import static subway.domain.init.InitSection.GANGNAM_TO_YEOKSAM;
import static subway.domain.init.InitSection.GYODAE_TO_GANGNAM;
import static subway.domain.init.InitSection.GYODAE_TO_TERMINAL;
import static subway.domain.init.InitSection.TERMINAL_TO_YANGJAE;
import static subway.domain.init.InitSection.YANGJAE_TO_FOREST;
import static subway.domain.init.InitSection.YANGJAE_TO_MAEBONG;
import static subway.exception.ExceptionMessage.SECTION_NOT_FOUND;

import subway.domain.Section;
import java.util.List;

public class SectionRepository {

    private static final List<Section> sections = List.of(
            Section.withValues(
                    GYODAE_TO_GANGNAM.getStart(), GYODAE_TO_GANGNAM.getEnd(),
                    GYODAE_TO_GANGNAM.getDistance(), GYODAE_TO_GANGNAM.getTime()),
            Section.withValues(
                    GANGNAM_TO_YEOKSAM.getStart(), GANGNAM_TO_YEOKSAM.getEnd(),
                    GANGNAM_TO_YEOKSAM.getDistance(), GANGNAM_TO_YEOKSAM.getTime()),
            Section.withValues(
                    GYODAE_TO_TERMINAL.getStart(), GYODAE_TO_TERMINAL.getEnd(),
                    GYODAE_TO_TERMINAL.getDistance(), GYODAE_TO_TERMINAL.getTime()),
            Section.withValues(
                    TERMINAL_TO_YANGJAE.getStart(), TERMINAL_TO_YANGJAE.getEnd(),
                    TERMINAL_TO_YANGJAE.getDistance(), TERMINAL_TO_YANGJAE.getTime()),
            Section.withValues(
                    YANGJAE_TO_FOREST.getStart(), YANGJAE_TO_FOREST.getEnd(),
                    YANGJAE_TO_FOREST.getDistance(), YANGJAE_TO_FOREST.getTime()),
            Section.withValues(
                    YANGJAE_TO_MAEBONG.getStart(), YANGJAE_TO_MAEBONG.getEnd(),
                    YANGJAE_TO_MAEBONG.getDistance(), YANGJAE_TO_MAEBONG.getTime()),
            Section.withValues(
                    GANGNAM_TO_YANGJAE.getStart(), GANGNAM_TO_YANGJAE.getEnd(),
                    GANGNAM_TO_YANGJAE.getDistance(), GANGNAM_TO_YANGJAE.getTime())
    );

    public static List<Section> sections() {
        return sections;
    }

    public static Section findByStartAndEnd(final String startStationName, final String endStationName) {
        return sections().stream()
                .filter(section -> section.isSame(startStationName, endStationName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(SECTION_NOT_FOUND.getMessage()));
    }
}
