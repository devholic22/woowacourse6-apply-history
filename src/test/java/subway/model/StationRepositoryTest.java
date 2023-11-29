package subway.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static subway.exception.ExceptionMessage.STATION_NOT_FOUND;
import static subway.exception.ExceptionMessage.STATION_SAME_EXCEPTION;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import subway.domain.Station;
import subway.domain.repository.StationRepository;

public class StationRepositoryTest {

    @Test
    @DisplayName("Station 이름 조회 정상 검증")
    void findStationValidTest() {
        // given
        String stationName = "교대역";

        // when & then
        assertDoesNotThrow(() -> StationRepository.findByName(stationName));
    }

    @Test
    @DisplayName("역 비교 검증 - 다른 경우 정상 통과")
    void equalValidTest() {
        // given
        Station startStation = StationRepository.findByName("교대역");
        Station endStation = StationRepository.findByName("강남역");

        // when & then
        assertDoesNotThrow(() -> StationRepository.validateIsBothNotSame(startStation, endStation));
    }

    @Test
    @DisplayName("역 비교 검증 - 같은 경우 예외")
    void equalExceptionTest() {
        // given
        Station startStation = StationRepository.findByName("강남역");
        Station endStation = StationRepository.findByName("강남역");

        // when & then
        assertThatThrownBy(() -> StationRepository.validateIsBothNotSame(startStation, endStation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STATION_SAME_EXCEPTION.getMessage());
    }

    @ParameterizedTest(name = "입력값이 [{0}]일 시 예외가 발생하는가?")
    @NullAndEmptySource
    @ValueSource(strings = {"기흥역", "신갈역", "교대", "교대역 ", " 역삼역", "양재시민의 숲"})
    @DisplayName("Station 이름 조회 예외 검증")
    void findStationExceptionTest(final String input) {
        // when & then
        assertThatThrownBy(() -> StationRepository.findByName(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STATION_NOT_FOUND.getMessage());
    }
}
