package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.view.input.ConsoleInputView;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.ConsoleOutputView;
import vendingmachine.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();
        MachineController controller = new MachineController(inputView, outputView);

        controller.run();
    }
}
