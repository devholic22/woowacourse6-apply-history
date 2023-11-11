package christmas.model.policy;

import christmas.model.Day;
import christmas.model.order.Orders;

public interface DiscountPolicy {

    int discount(final Orders orders, final Day day);
    boolean isOrdersAndDayAvailable(final Orders orders, final Day day);
}
