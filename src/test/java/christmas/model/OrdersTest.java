package christmas.model;

import static christmas.model.menu.Menu.CAKE;
import static christmas.model.menu.Menu.COKE;
import static christmas.model.menu.MenuType.DESSERT;
import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.model.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrdersTest {

    @Nested
    @DisplayName("Orders 정상 테스트")
    class OrdersValidTest {

        @Test
        @DisplayName("정상 Orders 생성 테스트")
        void validOrdersInputTest() {
            // given
            String ordersInput = "초코케이크-5,제로콜라-3";

            // when & then
            assertDoesNotThrow(() -> Orders.from(ordersInput));
        }

        @Test
        @DisplayName("Orders의 총합 가격 계산 테스트")
        void calculateTotalCostTest() {
            // given
            String ordersInput = "초코케이크-5,제로콜라-3";
            int expectedCost = CAKE.getCost() * 5 + COKE.getCost() * 3;
            Orders orders = Orders.from(ordersInput);

            // when
            int cost = orders.calculateTotalCost();

            // then
            assertThat(cost).isEqualTo(expectedCost);
        }

        @Test
        @DisplayName("특정 타입의 주문 개수 파악 테스트")
        void calculateTypeOrderCountTest() {
            // given
            String ordersInput = "초코케이크-5,제로콜라-3";
            int expectedSize = 5;
            Orders orders = Orders.from(ordersInput);

            // when
            int size = orders.calculateTypeOrdersCount(DESSERT);

            // then
            assertThat(size).isEqualTo(expectedSize);
        }
    }

    @Nested
    @DisplayName("Orders 예외 테스트")
    class OrdersExceptionTest {

        @Test
        @DisplayName("중복되면 안 된다.")
        void duplicateExceptionTest() {
            // given
            String ordersInput = "초코케이크-2,초코케이크-3";

            // when & then
            assertThatThrownBy(() -> Orders.from(ordersInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @Test
        @DisplayName("주문 개수가 20개를 초과하면 안 된다.")
        void ordersSizeExceptionTest() {
            // given
            String ordersInput = "초코케이크-5,제로콜라-20";

            // when & then
            assertThatThrownBy(() -> Orders.from(ordersInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @Test
        @DisplayName("음료로만 구성되면 안 된다.")
        void onlyDrinkExceptionTest() {
            // given
            String ordersInput = "제로콜라-10,레드와인-2";

            // when & then
            assertThatThrownBy(() -> Orders.from(ordersInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @ParameterizedTest(name = "Orders 주문이 [" + "{0}" + "]일 시 예외가 발생하는가?")
        @DisplayName("null, 빈 문자, 공백 포함 문자, 잘못된 입력 시 예외가 발생한다.")
        @NullAndEmptySource
        @ValueSource(strings = {"  초코케이크 - 5 ", "abcde"})
        void cannotSplitInputExceptionTest(final String value) {
            // when & then
            assertThatThrownBy(() -> Orders.from(value)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }
    }
}
