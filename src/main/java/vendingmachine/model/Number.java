package vendingmachine.model;

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
            throw new IllegalArgumentException();
        }
    }

    private static void validateIsPositive(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
