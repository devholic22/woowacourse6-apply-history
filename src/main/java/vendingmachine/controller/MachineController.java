package vendingmachine.controller;

import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.askMachineMoney();
    }
}
