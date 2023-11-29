package subway.model;

import static org.assertj.core.api.Assertions.assertThat;
import static subway.domain.command.PathCommand.MINIMUM_DISTANCE;
import static subway.domain.command.PathCommand.MINIMUM_TIME;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.PathManager;
import subway.domain.Station;
import subway.domain.command.PathCommand;
import subway.domain.repository.StationRepository;
import java.util.List;

public class PathManagerTest {

    @Test
    @DisplayName("최단 거리 기능 검증")
    void minimumDistanceTest() {
        // given
        PathManager pathManager = PathManager.createDefault();
        Station startStation = StationRepository.findByName("교대역");
        Station endStation = StationRepository.findByName("매봉역");
        PathCommand command = MINIMUM_DISTANCE;

        // when
        List<String> path = pathManager.findPath(startStation, endStation, command);

        // then
        assertThat(path.containsAll(List.of("교대역", "강남역", "양재역", "매봉역"))).isTrue();
    }

    @Test
    @DisplayName("최단 시간 기능 검증")
    void minimumTimeTest() {
        // given
        PathManager pathManager = PathManager.createDefault();
        Station startStation = StationRepository.findByName("교대역");
        Station endStation = StationRepository.findByName("매봉역");
        PathCommand command = MINIMUM_TIME;

        // when
        List<String> path = pathManager.findPath(startStation, endStation, command);

        // then
        assertThat(path.containsAll(List.of("교대역", "남부터미널역", "양재역", "매봉역"))).isTrue();
    }
}
