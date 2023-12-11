package subway.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static subway.exception.ExceptionMessage.NEGATIVE_NUMBER_EXCEPTION;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import subway.domain.Distance;

public class DistanceTest {

    @ParameterizedTest(name = "입력값이 [{0}]일 시 생성되는가?")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("정상 생성 검증")
    void createValidTest(final int value) {
        // when & then
        assertDoesNotThrow(() -> Distance.from(value));
    }

    @ParameterizedTest(name = "입력값이 [{0}]일 시 예외가 발생하는가?")
    @ValueSource(ints = {0, -1})
    @DisplayName("예외 생성 검증")
    void createExceptionTest(final int value) {
        // when & then
        assertThatThrownBy(() -> Distance.from(value)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NEGATIVE_NUMBER_EXCEPTION.getMessage());
    }
}
