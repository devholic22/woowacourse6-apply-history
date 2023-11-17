package vendingmachine.view.output;

import vendingmachine.model.dto.CoinResponse;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    @Override
    public void askMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    @Override
    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public void printMachineCoins(final List<CoinResponse> responses) {
        System.out.println();
        System.out.println("자판기가 보유한 동전");
        for (CoinResponse response : responses) {
            System.out.println(response.value() + "원 - " + response.size() + "개");
        }
    }

    @Override
    public void askProducts() {
        System.out.println();
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    }
}
