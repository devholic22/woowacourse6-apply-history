package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import christmas.model.Day;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekEndPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = new WeekEndPolicy();
    }

    @Test
    @DisplayName("주말 할인 정상")
    void weekEndDiscountTest() {
        // given
        Day requestDay = Day.from("1");
        Orders orders = Orders.from("제로콜라-2,초코케이크-1,바비큐립-1");
        int expectedCost = 2023;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertAll(
                () -> assertThat(discountCost).isEqualTo(expectedCost),
                () -> assertThat(discountPolicy.canDiscount(requestDay, orders)).isTrue()
        );
    }

    @ParameterizedTest(name = "날짜가 {0}일이고 메뉴 주문이 [{1}]인 경우")
    @CsvSource(value = {
            "3, 제로콜라-2,초코케이크-1,바비큐립-1",
            "1, 제로콜라-2,초코케이크-3"
    }, delimiterString = ", ")
    @DisplayName("주말이 아니거나 메인 메뉴가 없을 시 할인이 적용되지 않는다.")
    void notWeekEndTest(final String dayInput, final String ordersInput) {
        // given
        Day requestDay = Day.from(dayInput);
        Orders orders = Orders.from(ordersInput);

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertAll(
                () -> assertThat(discountCost).isEqualTo(0),
                () -> assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse()
        );
    }
}
