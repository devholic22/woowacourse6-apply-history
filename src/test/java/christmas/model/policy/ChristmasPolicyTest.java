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
        int expectedCost = 1000 + (20 - 1) * 100;

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
            "26, 제로콜라-2,초코케이크-1",
            "10, 타파스-1,제로콜라-1"
    }, delimiterString = ", ")
    @DisplayName("날짜가 지나거나 가격이 미달될 시 할인이 적용되지 않는다.")
    void notDiscountTest(final String dayInput, final String ordersInput) {
        // given
        Day requestDay = Day.from(dayInput);
        Orders orders = Orders.from(ordersInput);

        // when
        int discountCost = discountPolicy.discount(requestDay, orders);

        // then
        assertAll(
                () -> assertThat(discountCost).isEqualTo(0),
                () ->  assertThat(discountPolicy.canDiscount(requestDay, orders)).isFalse()
        );
    }
}
