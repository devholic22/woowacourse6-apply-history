package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeekDayPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = WeekDayPolicy.getInstance();
    }

    @Test
    @DisplayName("평일 할인 테스트")
    void weekDayDiscountTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");
        int expectedCost = 2_023;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(expectedCost);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isTrue();
    }

    @Test
    @DisplayName("평일 할인 테스트 - 평일이 아닐 시 지원 안 됨")
    void notWeekDayTest() {
        // given
        Day requestDay = Day.from("1");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isFalse();
    }

    @Test
    @DisplayName("평일 할인 테스트 - 디저트가 없을 시 지원 안 됨")
    void notInDessertTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("제로콜라-2,바비큐립-1");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.isCanDiscount(requestDay, orders)).isFalse();
    }
}
