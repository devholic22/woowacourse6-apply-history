package subway.domain.repository;

import static subway.exception.ExceptionMessage.SECTION_NOT_FOUND;

import subway.domain.Section;
import subway.domain.init.InitSection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SectionRepository {

    private static final List<Section> sections = Arrays.stream(InitSection.values())
            .map(section -> Section.withValues(
                    section.getStart(),
                    section.getEnd(),
                    section.getDistance(),
                    section.getTime())
            )
            .collect(Collectors.toList());

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
