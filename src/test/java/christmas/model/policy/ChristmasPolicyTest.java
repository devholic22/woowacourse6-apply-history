package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = ChristmasPolicy.getInstance();
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 테스트")
    void christmasDiscountTest() {
        // given
        Day requestDay = Day.from("20");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");
        int expectedCost = 1_000 + (20 - 1) * 100;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(expectedCost);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isTrue();
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 테스트 - 지원 안 됨")
    void notSupportDiscountTest() {
        // given
        Day requestDay = Day.from("26");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isFalse();
    }
}
