package christmas.view.output;

public interface OutputView {

    void printWelcome();
    void askRequestDay();
    void printExceptionMessage(final String message);
    void askOrderMenus();
    void printPreviewOrderAnswer(final String day);
}
