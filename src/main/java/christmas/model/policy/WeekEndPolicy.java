package christmas.model.policy;

import static christmas.model.calender.Calendar.WEEK_END;
import static christmas.model.calender.Calendar.findAllTypesByDay;
import static christmas.model.menu.MenuType.MAIN_DISH;

import christmas.model.Day;
import christmas.model.order.Orders;
import java.util.List;

public class WeekEndPolicy implements DiscountPolicy {

    private static final int EACH_MENU_DISCOUNT = 2_023;
    private static final int MINIMUM_COST = 10_000;
    private static final int NOT_DISCOUNT = 0;
    private static final int NOT_ORDERED = 0;

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
        List<String> calendarTypes = findAllTypesByDay(day);

        return totalCost >= MINIMUM_COST && calendarTypes.contains(WEEK_END.name()) && mainOrders != NOT_ORDERED;
    }
}
