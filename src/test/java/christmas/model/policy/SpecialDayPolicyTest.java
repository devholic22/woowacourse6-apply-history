package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDayPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = SpecialDayPolicy.getInstance();
    }

    @Test
    @DisplayName("특별 할인 정상")
    void specialDiscountTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("바비큐립-1,티본스테이크-2");
        int expectedCost = 1_000;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(expectedCost);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isTrue();
    }

    @Test
    @DisplayName("특별 할인 - 지원 안 됨")
    void notSupportDiscountTest() {
        // given
        Day requestDay = Day.from("1");
        Orders orders = Orders.from("바비큐립-1,티본스테이크-2");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isFalse();
    }
}
