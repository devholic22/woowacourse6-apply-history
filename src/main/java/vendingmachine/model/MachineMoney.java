package vendingmachine.model;

import static vendingmachine.exception.ExceptionMessage.DIVIDE_EXCEPTION;
import static vendingmachine.exception.ExceptionMessage.NOT_ENOUGH_NUMBER;

public class MachineMoney {

    private static final int MINIMUM_NUMBER = 10;
    private static final int DIVIDE_ZERO_NUMBER = 10;

    private final Number machineMoney;

    private MachineMoney(final Number machineMoney) {
        this.machineMoney = machineMoney;
    }

    public static MachineMoney from(final Number machineMoney) {
        validateIsNumberEnough(machineMoney);
        validateIsDivideZero(machineMoney);

        return new MachineMoney(machineMoney);
    }

    private static void validateIsNumberEnough(final Number machineMoney) {
        if (!machineMoney.canMinus(MINIMUM_NUMBER)) {
            throw new IllegalArgumentException(NOT_ENOUGH_NUMBER.getMessage());
        }
    }

    private static void validateIsDivideZero(final Number machineMoney) {
        if (!machineMoney.isDivideRemainZero(DIVIDE_ZERO_NUMBER)) {
            throw new IllegalArgumentException(DIVIDE_EXCEPTION.getMessage());
        }
    }

    public Number getMoney() {
        return machineMoney;
    }
}
