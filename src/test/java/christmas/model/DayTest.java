package christmas.model;

import static christmas.view.exception.InputException.BAD_DAY_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class DayTest {

    @Test
    @DisplayName("올바른 범위의 Day 생성 테스트")
    void validRangeDayTest() {
        // when & then
        assertDoesNotThrow(() -> Day.from("10"));
    }

    @Test
    @DisplayName("기간 계산 테스트")
    void calculateDurationTest() {
        // given
        Day requestDay = Day.from("10");
        int anotherDay = 20;
        int expectedDuration = 10;

        // when
        int duration = requestDay.calculateDuration(anotherDay);

        // then
        assertThat(duration).isEqualTo(expectedDuration);
    }

    @Test
    @DisplayName("특정 날짜가 지났는지 계산 테스트")
    void calculatePassedDayTest() {
        // given
        Day requestDay = Day.from("10");
        int anotherDay = 20;

        // when
        boolean passedAnswer = requestDay.isDayPassed(anotherDay);

        // then
        assertThat(passedAnswer).isFalse();
    }

    @Nested
    @DisplayName("Day 예외 테스트")
    class DayExceptionTest {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"abc", " 1 0 "})
        @DisplayName("일반 문자, 빈 문자, 공백 포함 문자, null인 경우 예외가 발생한다.")
        void notNumberExceptionTest(final String value) {
            // when & then
            assertThatThrownBy(() -> Day.from(value)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_DAY_EXCEPTION.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"50", "-20"})
        @DisplayName("범위가 올바르지 않을 경우 예외가 발생한다.")
        void unValidRangeExceptionTest(final String value) {
            // when & then
            assertThatThrownBy(() -> Day.from(value)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_DAY_EXCEPTION.getMessage());
        }
    }
}
