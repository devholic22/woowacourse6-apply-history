package vendingmachine.controller;

import vendingmachine.model.MachineMoney;
import vendingmachine.model.Number;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;
import java.util.function.Supplier;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        MachineMoney machineMoney = initMachineMoney();
    }

    public MachineMoney initMachineMoney() {
        return createInstance(() -> {
            Number machineNumber = initMachineNumber();
            return MachineMoney.from(machineNumber);
        });
    }

    private Number initMachineNumber() {
        return createInstance(() -> {
            outputView.askMachineMoney();
            return Number.from(inputView.readLine());
        });
    }

    private <T> T createInstance(final Supplier<T> creator) {
        T created = null;
        while (created == null) {
            created = tryGetInstance(creator, created);
        }
        return created;
    }

    private <T> T tryGetInstance(final Supplier<T> creator, T created) {
        try {
            created = creator.get();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
        }
        return created;
    }
}
