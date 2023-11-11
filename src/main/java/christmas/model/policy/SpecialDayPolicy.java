package christmas.model.policy;

import static christmas.model.calender.Calendar.SPECIAL_DAY;

import christmas.model.Day;
import christmas.model.order.Orders;

public class SpecialDayPolicy implements DiscountPolicy {

    private static final int MINIMUM_COST = 10_000;
    private static final int DISCOUNT = -1_000;
    private static final int NOT_DISCOUNT = 0;

    @Override
    public int discount(final Orders orders, final Day day) {
        if (isOrdersAndDayAvailable(orders, day)) {
            return DISCOUNT;
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isOrdersAndDayAvailable(final Orders orders, final Day day) {
        int totalCost = orders.getTotalCost();
        return totalCost >= MINIMUM_COST && day.isDayInCalendarType(SPECIAL_DAY);
    }
}
