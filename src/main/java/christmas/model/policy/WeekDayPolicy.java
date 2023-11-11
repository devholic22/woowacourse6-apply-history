package christmas.model.policy;

import static christmas.model.calender.Calendar.WEEK_DAY;
import static christmas.model.menu.MenuType.DESSERT;

import christmas.model.Day;
import christmas.model.order.Orders;

public class WeekDayPolicy implements DiscountPolicy {

    private static final int EACH_MENU_DISCOUNT = -2_023;
    private static final int MINIMUM_COST = 10_000;
    private static final int NOT_DISCOUNT = 0;
    private static final int NOT_ORDERED = 0;

    @Override
    public int discount(final Orders orders, final Day day) {
        if (isOrdersAndDayAvailable(orders, day)) {
            int dessertOrders = orders.calculateTypeOrdersCount(DESSERT);
            return EACH_MENU_DISCOUNT * dessertOrders;
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isOrdersAndDayAvailable(final Orders orders, final Day day) {
        int totalCost = orders.getTotalCost();
        int dessertOrders = orders.calculateTypeOrdersCount(DESSERT);

        return totalCost >= MINIMUM_COST && day.isDayInCalendarType(WEEK_DAY) && dessertOrders != NOT_ORDERED;
    }
}
