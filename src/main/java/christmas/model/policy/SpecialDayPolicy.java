package christmas.model.policy;

import static christmas.model.calender.Calendar.SPECIAL_DAY;

import christmas.model.Day;

public class SpecialDayPolicy implements DiscountPolicy {

    private static final int MINIMUM_COST = 10_000;

    @Override
    public boolean isCostAndDayAvailable(final int cost, final Day day) {
        return cost >= MINIMUM_COST && day.isDayInCalendarType(SPECIAL_DAY);
    }
}
