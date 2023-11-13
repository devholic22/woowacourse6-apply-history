package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = new ChristmasPolicy();
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 정상")
    void christmasDiscountTest() {
        // given
        Day requestDay = Day.from("20");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");
        int expectedCost = 1_000 + (20 - 1) * 100;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(expectedCost);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isTrue();
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 - 지원 안 됨")
    void notSupportDiscountTest() {
        // given
        Day requestDay = Day.from("26");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse();
    }
}
