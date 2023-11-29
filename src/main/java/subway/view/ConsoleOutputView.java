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
    public void askCommand() {
        System.out.println(TITLE_PREFIX + "원하는 기능을 선택하세요.");
    }

    @Override
    public void printPathCommands(final List<CommandResponse> commands) {
        System.out.println();
        System.out.println(TITLE_PREFIX + "경로 기준");
        for (CommandResponse command : commands) {
            System.out.println(command.getCommand() + COMMAND_WORD_DIVIDER + command.getName());
        }
        System.out.println();
    }

    @Override
    public void askStartStation() {
        System.out.println();
        System.out.println(TITLE_PREFIX + "출발역을 입력하세요.");
    }

    @Override
    public void askEndStation() {
        System.out.println();
        System.out.println(TITLE_PREFIX + "도착역을 입력하세요.");
    }

    @Override
    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
