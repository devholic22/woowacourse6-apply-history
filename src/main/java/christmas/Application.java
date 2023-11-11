package christmas;

import christmas.controller.EventController;
import christmas.view.input.ConsoleInputView;
import christmas.view.input.InputView;
import christmas.view.output.ConsoleOutputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();

        EventController controller = new EventController(inputView, outputView);

        controller.start();
    }
}
