package christmas.model.policy;

import static christmas.model.calender.Calendar.WEEK_END;
import static christmas.model.menu.MenuType.MAIN_DISH;

import christmas.model.Day;
import christmas.model.order.Orders;

public class WeekEndPolicy implements DiscountPolicy {

    private static final int EACH_MENU_DISCOUNT = -2_023;
    private static final int MINIMUM_COST = 10_000;
    private static final int NOT_DISCOUNT = 0;
    private static final int NOT_ORDERED = 0;

    @Override
    public int discount(final Orders orders, final Day day) {
        if (isOrdersAndDayAvailable(orders, day)) {
            int mainOrders = orders.calculateTypeOrdersCount(MAIN_DISH);
            return EACH_MENU_DISCOUNT * mainOrders;
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isOrdersAndDayAvailable(final Orders orders, final Day day) {
        int totalCost = orders.getTotalCost();
        int mainOrders = orders.calculateTypeOrdersCount(MAIN_DISH);

        return totalCost >= MINIMUM_COST && day.isDayInCalendarType(WEEK_END) && mainOrders != NOT_ORDERED;
    }
}
