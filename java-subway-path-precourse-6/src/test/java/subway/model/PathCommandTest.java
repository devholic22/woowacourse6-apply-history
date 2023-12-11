package subway.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static subway.exception.ExceptionMessage.COMMAND_NOT_FOUND;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import subway.domain.command.PathCommand;

public class PathCommandTest {

    @ParameterizedTest(name="입력값이 [{0}]일 시 정상 조회되는가?")
    @ValueSource(strings = {"1", "2", "B"})
    @DisplayName("명령어 조회 정상 검증")
    void findByCommandInputValidTest(final String input) {
        // when
        PathCommand findCommand = PathCommand.findByCommandInput(input);

        // then
        assertThat(findCommand.getCommand()).isEqualTo(input);
    }

    @ParameterizedTest(name="입력값이 [{0}]일 시 예외가 발생하는가?")
    @ValueSource(strings = {"abc", "b", "1 ", " 2", " B "})
    @NullAndEmptySource
    @DisplayName("명령어 조회 실패 검증")
    void findByCommandExceptionTest(final String input) {
        // when & then
        assertThatThrownBy(() -> PathCommand.findByCommandInput(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(COMMAND_NOT_FOUND.getMessage());
    }
}
