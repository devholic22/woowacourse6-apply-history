package subway.domain;

import static subway.exception.ExceptionMessage.NEGATIVE_NUMBER_EXCEPTION;

public class Distance {

    private final int distance;

    private Distance(final int distance) {
        this.distance = distance;
    }

    public static Distance from(final int value) {
        validateIsPositive(value);
        return new Distance(value);
    }

    private static void validateIsPositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_EXCEPTION.getMessage());
        }
    }

    public int getDistance() {
        return distance;
    }
}
