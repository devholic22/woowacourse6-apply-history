package christmas.model;

import static christmas.view.exception.InputException.BAD_DAY_EXCEPTION;

import christmas.model.calender.Calendar;
import java.util.List;

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

    public boolean isDayPassed(final int day) {
        return this.day > day;
    }

    public boolean isDayInCalendarType(final Calendar calendarType) {
        List<Calendar> calendarTypes = Calendar.findAllByDay(day);
        return calendarTypes.contains(calendarType);
    }

    public int calculateDuration(final int day) {
        return Math.abs(day - this.day);
    }

    public int getDay() {
        return day;
    }
}
