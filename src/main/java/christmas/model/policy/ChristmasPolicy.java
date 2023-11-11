package christmas.model.policy;

import christmas.model.Day;
import christmas.model.order.Orders;

public class ChristmasPolicy implements DiscountPolicy {

    private static final int START_DAY = 1;
    private static final int CHRISTMAS_DAY = 25;
    private static final int MINIMUM_COST = 10_000;
    private static final int DEFAULT_DISCOUNT = 1_000;
    private static final int EACH_DAY_DISCOUNT = 100;
    private static final int NOT_DISCOUNT = 0;

    private static ChristmasPolicy singleton;

    private ChristmasPolicy() {
    }

    public static ChristmasPolicy getInstance() {
        if (singleton == null) {
            singleton = new ChristmasPolicy();
        }
        return singleton;
    }

    @Override
    public int discount(final Day day, final Orders orders) {
        if (isOrdersAndDayAvailable(day, orders)) {
            return DEFAULT_DISCOUNT + (day.calculateDuration(START_DAY) * EACH_DAY_DISCOUNT);
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isOrdersAndDayAvailable(final Day day, final Orders orders) {
        int totalCost = orders.getTotalCost();
        return totalCost >= MINIMUM_COST && !day.isDayPassed(CHRISTMAS_DAY);
    }
}
