package vendingmachine.view.output;

public class ConsoleOutputView implements OutputView {

    @Override
    public void askMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    @Override
    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
