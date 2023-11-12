package christmas.model.policy;

import static christmas.model.calender.Calendar.SPECIAL_DAY;
import static christmas.model.calender.Calendar.findAllTypesByDay;

import christmas.model.Day;
import christmas.model.order.Orders;
import java.util.List;

public class SpecialDayPolicy implements DiscountPolicy {

    private static final int MINIMUM_COST = 10_000;
    private static final int DISCOUNT = 1_000;
    private static final int NOT_DISCOUNT = 0;

    private static SpecialDayPolicy singleton;

    private SpecialDayPolicy() {
    }

    public static SpecialDayPolicy getInstance() {
        if (singleton == null) {
            singleton = new SpecialDayPolicy();
        }
        return singleton;
    }

    @Override
    public int discount(final Day day, final Orders orders) {
        if (isCanDiscount(day, orders)) {
            return DISCOUNT;
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isCanDiscount(final Day day, final Orders orders) {
        int totalCost = orders.calculateTotalCost();
        List<String> calendarTypes = findAllTypesByDay(day);

        return totalCost >= MINIMUM_COST && calendarTypes.contains(SPECIAL_DAY.name());
    }
}
