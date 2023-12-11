package subway.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static subway.exception.ExceptionMessage.SECTION_NOT_FOUND;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.repository.SectionRepository;

public class SectionRepositoryTest {

    @Test
    @DisplayName("구간 조회 정상 검증")
    void searchValidTest() {
        // given
        String startStation = "교대역";
        String endStation = "남부터미널역";

        // when & then
        assertDoesNotThrow(() -> SectionRepository.findByStartAndEnd(startStation, endStation));
    }

    @Test
    @DisplayName("구간 조회 실패 검증 - 없음")
    void searchNotFoundTest() {
        // given
        String startStation = "교대역";
        String endStation = "양재역";

        // when & then
        assertThatThrownBy(() -> SectionRepository.findByStartAndEnd(startStation, endStation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SECTION_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("구간 조회 실패 검증 - 중복")
    void searchDuplicateTest() {
        // given
        String startStation = "교대역";
        String endStation = "교대역";

        // when & then
        assertThatThrownBy(() -> SectionRepository.findByStartAndEnd(startStation, endStation))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(SECTION_NOT_FOUND.getMessage());
    }
}
