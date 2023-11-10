package christmas.controller;

import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printWelcome();
        outputView.askRequestDay();
    }
}
