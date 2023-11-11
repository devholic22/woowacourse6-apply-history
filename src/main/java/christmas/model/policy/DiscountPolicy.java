package christmas.model.policy;

import christmas.model.Day;

public interface DiscountPolicy {

    boolean isCostAndDayAvailable(final int cost, final Day day);
}
