package vendingmachine.model;

import static vendingmachine.exception.ExceptionMessage.DIVIDE_EXCEPTION;
import static vendingmachine.exception.ExceptionMessage.NOT_ENOUGH_NUMBER;

public class Cost {

    private static final int MINIMUM_NUMBER = 100;
    private static final int DIVIDE_ZERO_NUMBER = 10;

    private final Number cost;

    private Cost(final Number cost) {
        this.cost = cost;
    }

    public static Cost from(final String costInput) {
        Number cost = Number.from(costInput);
        validateIsNumberEnough(cost);
        validateIsDivideZero(cost);

        return new Cost(cost);
    }

    private static void validateIsNumberEnough(final Number costMoney) {
        if (!costMoney.canMinus(MINIMUM_NUMBER)) {
            throw new IllegalArgumentException(NOT_ENOUGH_NUMBER.getMessage());
        }
    }

    private static void validateIsDivideZero(final Number costMoney) {
        if (!costMoney.isDivideRemainZero(DIVIDE_ZERO_NUMBER)) {
            throw new IllegalArgumentException(DIVIDE_EXCEPTION.getMessage());
        }
    }

    public boolean isMoneyCanBuy(final int value) {
        return cost.getNumber() <= value;
    }

    public int getCost() {
        return cost.getNumber();
    }
}
