package vendingmachine.model;

import static vendingmachine.exception.ExceptionMessage.NOT_POSITIVE_NUMBER;
import static vendingmachine.exception.ExceptionMessage.NUMBER_FORMAT_EXCEPTION;

public class Number {

    private int number;

    private Number(final int number) {
        this.number = number;
    }

    public static Number from(final String numberInput) {
        int number = convertToNumber(numberInput);
        validateIsPositive(number);

        return new Number(number);
    }

    private static int convertToNumber(final String numberInput) {
        try {
            return Integer.parseInt(numberInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(NUMBER_FORMAT_EXCEPTION.getMessage());
        }
    }

    private static void validateIsPositive(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_POSITIVE_NUMBER.getMessage());
        }
    }

    public void minus(final int number) {
        if (canMinus(number)) {
            this.number -= number;
        }
    }

    public boolean canMinus(final int number) {
        return this.number >= number;
    }

    public boolean isDivideRemainZero(final int number) {
        return this.number % number == 0;
    }

    public int getNumber() {
        return number;
    }
}
