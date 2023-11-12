package christmas.model.policy;

import static christmas.model.calender.Calendar.WEEK_END;
import static christmas.model.menu.MenuType.MAIN_DISH;

import christmas.model.Day;
import christmas.model.order.Orders;

public class WeekEndPolicy implements DiscountPolicy {

    private static final int EACH_MENU_DISCOUNT = 2_023;
    private static final int MINIMUM_COST = 10_000;
    private static final int NOT_DISCOUNT = 0;
    private static final int NOT_ORDERED = 0;

    private static WeekEndPolicy singleton;

    private WeekEndPolicy() {
    }

    public static WeekEndPolicy getInstance() {
        if (singleton == null) {
            singleton = new WeekEndPolicy();
        }
        return singleton;
    }

    @Override
    public int discount(final Day day, final Orders orders) {
        if (isCanDiscount(day, orders)) {
            int mainOrders = orders.calculateTypeOrdersCount(MAIN_DISH);
            return EACH_MENU_DISCOUNT * mainOrders;
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isCanDiscount(final Day day, final Orders orders) {
        int totalCost = orders.calculateTotalCost();
        int mainOrders = orders.calculateTypeOrdersCount(MAIN_DISH);

        return totalCost >= MINIMUM_COST && day.isDayInCalendarType(WEEK_END) && mainOrders != NOT_ORDERED;
    }
}
