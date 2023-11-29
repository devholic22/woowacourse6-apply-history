package subway.view;

import subway.domain.dto.CommandResponse;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    private static final String TITLE_PREFIX = "## ";
    private static final String COMMAND_WORD_DIVIDER = ". ";
    private static final String RESULT_HEADER_LINE = "---";
    private static final String RESULT_PREFIX = "[INFO] ";
    private static final String TOTAL_PREFIX = "총 ";
    private static final String DISTANCE_TITLE = "거리";
    private static final String RESULT_DIVIDER = ": ";
    private static final String DISTANCE_UNIT = "km";
    private static final String TIME_TITLE = "소요 시간";
    private static final String TIME_UNIT = "분";

    @Override
    public void printMainScreen(final List<CommandResponse> commands) {
        System.out.println();
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
    public void printResult(final List<String> stations, final int distance, final int time) {
        System.out.println();
        System.out.println(TITLE_PREFIX + "조회 결과");
        System.out.println(RESULT_PREFIX + RESULT_HEADER_LINE);
        System.out.println(RESULT_PREFIX + TOTAL_PREFIX + DISTANCE_TITLE + RESULT_DIVIDER + distance + DISTANCE_UNIT);
        System.out.println(RESULT_PREFIX + TOTAL_PREFIX + TIME_TITLE + RESULT_DIVIDER + time + TIME_UNIT);
        System.out.println(RESULT_PREFIX + RESULT_HEADER_LINE);
        for (String station : stations) {
            System.out.println(RESULT_PREFIX + station);
        }
    }

    @Override
    public void printExceptionMessage(final String message) {
        System.out.println();
        System.out.println(message);
    }
}
