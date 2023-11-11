package christmas.model.policy;

import christmas.model.Day;

public class ChristmasPolicy implements DiscountPolicy {

    private static final int CHRISTMAS_DAY = 25;
    private static final int MINIMUM_COST = 10_000;

    @Override
    public boolean isCostAndDayAvailable(final int cost, final Day day) {
        return cost >= MINIMUM_COST && !day.isDayPassed(CHRISTMAS_DAY);
    }
}
