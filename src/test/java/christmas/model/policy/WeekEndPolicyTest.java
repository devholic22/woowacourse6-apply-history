package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekEndPolicyTest {

    private static final String WEEK_END = "1";
    private static final String WEEK_DAY = "3";

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = new WeekEndPolicy();
    }

    @Test
    @DisplayName("주말 할인 정상")
    void weekEndDiscountTest() {
        // given
        Day requestDay = Day.from(WEEK_END);
        Orders orders = Orders.from("제로콜라-2,초코케이크-1,바비큐립-1");
        int expectedCost = 2_023;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(expectedCost);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isTrue();
    }

    @Test
    @DisplayName("주말 할인 - 주말이 아닐 시 지원 안 됨")
    void notWeekEndTest() {
        // given
        Day requestDay = Day.from(WEEK_DAY);
        Orders orders = Orders.from("제로콜라-2,초코케이크-1,바비큐립-1");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse();
    }

    @Test
    @DisplayName("주말 할인 - 메인 메뉴가 없을 시 지원 안 됨")
    void notInMainDishTest() {
        // given
        Day requestDay = Day.from(WEEK_END);
        Orders orders = Orders.from("제로콜라-2,초코케이크-3");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse();
    }
}
