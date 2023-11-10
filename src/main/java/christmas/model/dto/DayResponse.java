package christmas.model.dto;

public record DayResponse(String day) {

    public static DayResponse from(final int day) {
        return new DayResponse(String.valueOf(day));
    }
}
