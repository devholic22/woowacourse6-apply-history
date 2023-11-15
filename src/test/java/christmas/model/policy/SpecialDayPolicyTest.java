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

class SpecialDayPolicyTest {

    private DiscountPolicy discountPolicy;

    @BeforeEach
    void beforeEach() {
        discountPolicy = new SpecialDayPolicy();
    }

    @ParameterizedTest(name = "날짜가 {0}일인 경우")
    @CsvSource(value = {"3", "10", "17", "24", "25", "31"})
    @DisplayName("특별 할인 정상")
    void specialDiscountTest(final String dayInput) {
        // given
        Day requestDay = Day.from(dayInput);
        Orders orders = Orders.from("바비큐립-1,티본스테이크-2");
        int expectedCost = 1_000;

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertAll(
                () -> assertThat(discountCost).isEqualTo(expectedCost),
                () -> assertThat(discountPolicy.canDiscount(requestDay, orders)).isTrue()
        );
    }

    @Test
    @DisplayName("특별 할인 - 지원 안 됨 (특별한 날이 아님)")
    void notSupportDiscountTest() {
        // given
        Day requestDay = Day.from("1");
        Orders orders = Orders.from("바비큐립-1,티본스테이크-2");

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertAll(
                () -> assertThat(discountCost).isEqualTo(0),
                () -> assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse()
        );
    }
}
