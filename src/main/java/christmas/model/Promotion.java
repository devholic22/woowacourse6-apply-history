package christmas.model;

import christmas.model.order.Orders;
import christmas.model.policy.ChristmasPolicy;
import christmas.model.policy.DiscountPolicy;
import christmas.model.policy.SpecialDayPolicy;
import christmas.model.policy.WeekDayPolicy;
import christmas.model.policy.WeekEndPolicy;
import java.util.Arrays;
import java.util.List;

public enum Promotion {

    CHRISTMAS("크리스마스 디데이 할인", ChristmasPolicy.getInstance()),
    WEEK_DAY("평일 할인", WeekDayPolicy.getInstance()),
    WEEK_END("주말 할인", WeekEndPolicy.getInstance()),
    SPECIAL("특별 할인", SpecialDayPolicy.getInstance());

    private final String name;
    private final DiscountPolicy discountPolicy;

    Promotion(final String name, final DiscountPolicy discountPolicy) {
        this.name = name;
        this.discountPolicy = discountPolicy;
    }

    public static String findNameByPolicy(final DiscountPolicy discountPolicy) {
        for (Promotion promotion : values()) {
            if (discountPolicy.equals(promotion.discountPolicy)) {
                return promotion.name;
            }
        }
        throw new IllegalArgumentException();
    }

    public static List<DiscountPolicy> collectPoliciesByRequest(final Day visitDay, final Orders requestOrders) {
        List<Promotion> promotions = Arrays.asList(values());
        return promotions.stream()
                .map(promotion -> promotion.discountPolicy)
                .filter(policy -> policy.isCanDiscount(visitDay, requestOrders))
                .toList();
    }

    public DiscountPolicy getPolicy() {
        return discountPolicy;
    }
}
