package subway.domain;

import static subway.exception.ExceptionMessage.NEGATIVE_NUMBER_EXCEPTION;

public class Time {

    private final int time;

    private Time(final int time) {
        this.time = time;
    }

    public static Time from(final int value) {
        validateIsPositive(value);
        return new Time(value);
    }

    private static void validateIsPositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_EXCEPTION.getMessage());
        }
    }

    public int getTime() {
        return time;
    }
}
