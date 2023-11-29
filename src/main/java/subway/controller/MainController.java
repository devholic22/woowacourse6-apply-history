package subway.controller;

import subway.view.OutputView;
import java.util.Scanner;

public class MainController {

    private final Scanner scanner;
    private final OutputView outputView;

    public MainController(final Scanner scanner, final OutputView outputView) {
        this.scanner = scanner;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printMainScreen();
    }
}
