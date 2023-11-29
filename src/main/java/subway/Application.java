package subway;

import subway.controller.MainController;
import subway.view.ConsoleOutputView;
import subway.view.OutputView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final OutputView outputView = new ConsoleOutputView();
        // TODO: 프로그램 구현
        final MainController controller = new MainController(scanner, outputView);

        controller.run();
    }
}
