package vendingmachine.view.output;

import vendingmachine.model.dto.CoinResponse;
import java.util.List;

public interface OutputView {

    void askMachineMoney();
    void printExceptionMessage(final String message);
    void printMachineCoins(final List<CoinResponse> responses);
}
