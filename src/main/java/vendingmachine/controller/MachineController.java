package vendingmachine.controller;

import vendingmachine.model.CustomerMoney;
import vendingmachine.model.MachineMoney;
import vendingmachine.model.Number;
import vendingmachine.model.coin.CoinManager;
import vendingmachine.model.coin.Coins;
import vendingmachine.model.dto.CoinResponse;
import vendingmachine.model.product.Products;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;
import java.util.List;
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
        Coins machineCoins = Coins.from(machineMoney.getMoney());
        printMachineCoins(machineCoins);

        Products products = initProducts();
        CustomerMoney customerMoney = initCustomerMoney();
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

    private void printMachineCoins(final Coins machineCoins) {
        List<CoinResponse> coinResponses = CoinManager.convertCoinResponses(machineCoins);
        outputView.printMachineCoins(coinResponses);
    }

    private Products initProducts() {
        return createInstance(() -> {
            outputView.askProducts();
            return Products.from(inputView.readLine());
        });
    }

    private CustomerMoney initCustomerMoney() {
        return createInstance(() -> {
            outputView.askCustomerMoney();
            return CustomerMoney.from(inputView.readLine());
        });
    }
}
