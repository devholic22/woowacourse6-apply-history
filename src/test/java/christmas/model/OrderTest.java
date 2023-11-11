package christmas.model;

import static christmas.model.menu.Menu.COKE;
import static christmas.model.menu.Menu.SOUP;
import static christmas.model.menu.Menu.TAPAS;
import static christmas.model.menu.MenuType.APPETIZER;
import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.model.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {

    @Nested
    @DisplayName("Order 정상 테스트")
    class ValidOrderTest {

        @Test
        @DisplayName("정상 Order 생성 테스트")
        void validOrderTest() {
            // given
            String orderInput = "양송이수프-2";

            // when & then
            assertDoesNotThrow(() -> Order.from(orderInput));
        }

        @Test
        @DisplayName("Order 메뉴 타입 조회 정상 테스트")
        void orderTypeMatchTest() {
            // given
            String orderInput = "양송이수프-2";
            Order order = Order.from(orderInput);

            // when
            boolean matchType = order.isOrderType(APPETIZER);

            // then
            assertThat(matchType).isTrue();
        }

        @Test
        @DisplayName("Order 메뉴 개수 계산 정상 테스트")
        void orderSizeCalculateTest() {
            // given
            String orderInput = "양송이수프-2";
            int expectedSize = 2;
            Order order = Order.from(orderInput);

            // when
            int orderSize = order.getSize();

            // then
            assertThat(orderSize).isEqualTo(expectedSize);
        }

        @Test
        @DisplayName("Order 메뉴 가격 계산 정상 테스트")
        void orderCostCalculateTest() {
            // given
            String orderInput = SOUP.getName() + "-" + 2;
            int expectedSize = 2;
            Order order = Order.from(orderInput);

            // when
            int orderCost = order.calculateCost();

            // then
            assertThat(orderCost).isEqualTo(expectedSize * SOUP.getCost());
        }

        @Test
        @DisplayName("Order 메뉴 이름 조회 정상 테스트")
        void orderMenuNameTest() {
            // given
            String orderInput = "양송이수프-2";
            Order order = Order.from(orderInput);

            // when
            String menuName = order.getMenuName();

            // then
            assertThat(menuName).isEqualTo("양송이수프");
        }

        @Test
        @DisplayName("메뉴 이름만으로 Order 생성 테스트")
        void createOrderWithNameTest() {
            // given
            String nameInput = SOUP.getName();
            Order order = Order.createByName(nameInput);
            int expectedSize = 1;

            // when
            String menuName = order.getMenuName();
            int orderSize = order.getSize();

            // then
            assertThat(menuName).isEqualTo(nameInput);
            assertThat(orderSize).isEqualTo(expectedSize);
        }
    }

    @Nested
    @DisplayName("Order 예외 테스트")
    class OrderExceptionTest {

        @ParameterizedTest
        @ValueSource(strings = {"abc", "   ab  "})
        @NullAndEmptySource
        @DisplayName("잘못된 형식의 일반 문자, null, 빈 문자, 공백 포함 문자 등을 입력받으면 예외가 발생한다.")
        void unValidInputExceptionTest(final String value) {
            // when & then
            assertThatThrownBy(() -> Order.from(value)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @Test
        @DisplayName("없는 메뉴를 받으면 예외가 발생한다.")
        void notExistMenuExceptionTest() {
            // given
            String orderInput = "팝콘-5";

            // when & then
            assertThatThrownBy(() -> Order.from(orderInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @Test
        @DisplayName("메뉴가 있더라도 수량이 없으면 예외가 발생한다.")
        void notSizeInputExceptionTest() {
            // given
            String orderInput = COKE.getName();

            // when & then
            assertThatThrownBy(() -> Order.from(orderInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings={"abc", "   1  ", "0", "-1"})
        @NullAndEmptySource
        @DisplayName("수량의 값에 잘못된 값이 전달되면 예외가 발생한다. (일반 문자, null, 빈 문자, 공백 포함 문자, 1 미만)")
        void unValidSizeInputException(final String value) {
            // given
            String orderInput = TAPAS.getName() + "-" + value;

            // when & then
            assertThatThrownBy(() -> Order.from(orderInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }
    }
}
