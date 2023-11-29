package subway.view;

import subway.domain.dto.CommandResponse;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    private static final String TITLE_PREFIX = "## ";
    private static final String COMMAND_WORD_DIVIDER = ". ";

    @Override
    public void printMainScreen(final List<CommandResponse> commands) {
        System.out.println(TITLE_PREFIX + "메인 화면");
        for (CommandResponse command : commands) {
            System.out.println(command.getCommand() + COMMAND_WORD_DIVIDER + command.getName());
        }
        System.out.println();
    }

    @Override
    public void askInitCommand() {
        System.out.println(TITLE_PREFIX + "원하는 기능을 선택하세요.");
    }

    @Override
    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
