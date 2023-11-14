package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekDayPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = new WeekDayPolicy();
    }

    @Test
    @DisplayName("평일 할인 정상")
    void weekDayDiscountTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1");
        int expectedCost = 2_023;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(expectedCost);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isTrue();
    }

    @ParameterizedTest(name = "날짜가 {0}일이고 메뉴 주문이 [{1}]인 경우")
    @CsvSource(value = {
            "1, 제로콜라-2,초코케이크-1",
            "3, 제로콜라-2,바비큐립-1",
            "3, 아이스크림-1"
    }, delimiterString = ", ")
    @DisplayName("평일 할인 - 평일이 아니거나, 디저트가 없거나, 가격이 미달일 시 할인이 적용되지 않는다.")
    void notWeekDayTest(final String dayInput, final String ordersInput) {
        // given
        Day requestDay = Day.from(dayInput);
        Orders orders = Orders.from(ordersInput);

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertThat(discountCost).isEqualTo(0);
        assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse();
    }
}
