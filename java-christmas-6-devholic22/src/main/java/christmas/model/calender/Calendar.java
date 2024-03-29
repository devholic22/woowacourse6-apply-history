package christmas.model.calender;

import christmas.model.Day;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Calendar {

    WEEK_DAY(List.of(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31
    )),
    WEEK_END(List.of(
            1,2,
            8,9,
            15,16,
            22,23,
            29,30
    )),
    SPECIAL_DAY(List.of(
            3,
            10,
            17,
            24,25,
            31
    ));

    Calendar(final List<Integer> days) {
        this.days = days;
    }

    private final List<Integer> days;

    public static List<String> findAllTypesByDay(final Day day) {
        int dayValue = day.getDay();
        List<String> types = new ArrayList<>();

        Arrays.stream(values())
                .filter(calendar -> calendar.isHaveDay(dayValue))
                .forEach(calendar -> types.add(calendar.name()));

        return types;
    }

    public boolean isHaveDay(final int day) {
        return days.contains(day);
    }
}
