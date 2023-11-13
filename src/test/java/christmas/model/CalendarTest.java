package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.calender.Calendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class CalendarTest {

    @Test
    @DisplayName("Calendar 정상 조회 - 3일은 평일이자 특별한 날이다.")
    void validCalendarFindTest() {
        // given
        Day day = Day.from("3");

        // when
        List<String> calendarTypes = Calendar.findAllTypesByDay(day);

        // then
        assertThat(calendarTypes.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Calendar 정상 조회 - 30일은 주말이다.")
    void validCalendarFindTestAnother() {
        // given
        Day day = Day.from("30");

        // when
        List<String> calendarTypes = Calendar.findAllTypesByDay(day);

        // then
        assertThat(calendarTypes.size()).isEqualTo(1);
    }
}
