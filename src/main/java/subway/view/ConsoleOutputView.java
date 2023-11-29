package subway.view;

public class ConsoleOutputView implements OutputView {

    private static final String TITLE_PREFIX = "## ";

    @Override
    public void printMainScreen() {
        System.out.println(TITLE_PREFIX + "메인 화면");
    }
}
