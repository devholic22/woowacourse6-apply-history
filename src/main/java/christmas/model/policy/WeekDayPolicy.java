package christmas.model.policy;

import static christmas.model.calender.Calendar.WEEK_DAY;
import static christmas.model.calender.Calendar.findAllTypesByDay;
import static christmas.model.menu.MenuType.DESSERT;

import christmas.model.Day;
import christmas.model.order.Orders;
import java.util.List;

public class WeekDayPolicy implements DiscountPolicy {

    private static final int EACH_MENU_DISCOUNT = 2_023;
    private static final int MINIMUM_COST = 10_000;
    private static final int NOT_DISCOUNT = 0;
    private static final int NOT_ORDERED = 0;

    @Override
    public int discount(final Day day, final Orders orders) {
        if (isCanDiscount(day, orders)) {
            int dessertOrders = orders.calculateTypeOrdersCount(DESSERT);
            return EACH_MENU_DISCOUNT * dessertOrders;
        }
        return NOT_DISCOUNT;
    }

    @Override
    public boolean isCanDiscount(final Day day, final Orders orders) {
        int totalCost = orders.calculateTotalCost();
        int dessertOrders = orders.calculateTypeOrdersCount(DESSERT);
        List<String> calendarTypes = findAllTypesByDay(day);

        return totalCost >= MINIMUM_COST && calendarTypes.contains(WEEK_DAY.name()) && dessertOrders != NOT_ORDERED;
    }
}
