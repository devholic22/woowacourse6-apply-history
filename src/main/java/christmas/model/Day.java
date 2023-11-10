package christmas.model;

import static christmas.view.exception.InputException.BAD_DAY_EXCEPTION;

public class Day {

    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private final int day;

    private Day(final int day) {
        this.day = day;
    }

    public static Day from(final String dayInput) {
        int day = convertToNumber(dayInput);
        validateDayRangeValid(day);

        return new Day(day);
    }

    private static int convertToNumber(final String dayInput) {
        try {
            return Integer.parseInt(dayInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(BAD_DAY_EXCEPTION.getMessage());
        }
    }

    private static void validateDayRangeValid(final int day) {
        if (day < START_DAY || day > END_DAY) {
            throw new IllegalArgumentException(BAD_DAY_EXCEPTION.getMessage());
        }
    }

    public int getDay() {
        return day;
    }
}
