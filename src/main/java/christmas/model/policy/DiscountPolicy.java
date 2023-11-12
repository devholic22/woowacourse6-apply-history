package christmas.model.policy;

import christmas.model.Day;
import christmas.model.order.Orders;

public interface DiscountPolicy {

    int discount(final Day day, final Orders orders);
    boolean isCanDiscount(final Day day, final Orders orders);
}
