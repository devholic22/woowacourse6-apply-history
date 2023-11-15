package christmas.model;

import static christmas.model.menu.Menu.TAPAS;
import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import christmas.model.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    private static final String MENU_ORDERS =
    """
    양송이수프, 2, SOUP
    타파스, 2, TAPAS
    시저샐러드, 2, SALAD
    티본스테이크, 2, STEAK
    바비큐립, 2, BARBEQUE
    해산물파스타, 2, SEA_PASTA
    크리스마스파스타, 2, CHRISTMAS_PASTA
    초코케이크, 2, CAKE
    아이스크림, 2, ICECREAM
    제로콜라, 2, COKE
    레드와인, 2, WINE
    샴페인, 2, CHAMPAGNE
    """;

    @Nested
    @DisplayName("Order 정상")
    class ValidOrderTest {

        @ParameterizedTest(name = "{0} {1}개 주문 생성이 문제 없는가?")
        @CsvSource(textBlock = MENU_ORDERS)
        @DisplayName("정상 Order 생성")
        void validOrderTest(final String nameInput, final String sizeInput) {
            // given
            String orderInput = nameInput + "-" + sizeInput;

            // when & then
            assertDoesNotThrow(() -> Order.from(orderInput));
        }

        @ParameterizedTest(name = "{0} 메뉴 주문 시 저장된 메뉴 타입이 메뉴와 같은가?")
        @CsvSource(textBlock = MENU_ORDERS)
        @DisplayName("Order 메뉴 타입 조회 정상")
        void orderTypeMatchTest(final String nameInput, final String sizeInput, final Menu menu) {
            // given
            Order order = Order.from(nameInput + "-" + sizeInput);
            String type = menu.getType();

            // when
            boolean matchType = order.isOrderType(MenuType.findByTypeName(type));

            // then
            assertThat(matchType).isTrue();
        }

        @ParameterizedTest(name = "{0} {1}개 주문 시 저장된 개수가 입력 개수와 같은가?")
        @CsvSource(textBlock = MENU_ORDERS)
        @DisplayName("Order 메뉴 개수 계산 정상")
        void orderSizeCalculateTest(final String nameInput, final String sizeInput) {
            // given
            String orderInput = nameInput + "-" + sizeInput;
            int expectedSize = Integer.parseInt(sizeInput);
            Order order = Order.from(orderInput);

            // when
            int orderSize = order.getSize();

            // then
            assertThat(orderSize).isEqualTo(expectedSize);
        }

        @ParameterizedTest(name = "{0} {1}개 주문 시 저장된 총 가격이 예상과 같은가?")
        @CsvSource(textBlock = MENU_ORDERS)
        @DisplayName("Order 메뉴 가격 계산 정상")
        void orderCostCalculateTest(final String nameInput, final String sizeInput, final Menu menu) {
            // given
            String orderInput = nameInput + "-" + sizeInput;
            int expectedSize = Integer.parseInt(sizeInput);
            Order order = Order.from(orderInput);

            // when
            int orderCost = order.calculateCost();

            // then
            assertThat(orderCost).isEqualTo(expectedSize * menu.getCost());
        }

        @ParameterizedTest(name = "{0} 메뉴를 주문했을 때의 저장된 메뉴 이름")
        @CsvSource(textBlock = MENU_ORDERS)
        @DisplayName("Order 메뉴 이름 조회 정상")
        void orderMenuNameTest(final String nameInput, final String sizeInput) {
            // given
            String orderInput = nameInput + "-" + sizeInput;
            Order order = Order.from(orderInput);

            // when
            String menuName = order.getName();

            // then
            assertThat(menuName).isEqualTo(nameInput);
        }

        @ParameterizedTest(name = "{0} 메뉴 기본 생성 시 1개씩 저장되는가?")
        @EnumSource(value = Menu.class)
        @DisplayName("메뉴 이름만으로 Order 생성")
        void createOrderWithNameTest(final Menu menu) {
            // given
            String nameInput = menu.getName();
            Order order = Order.createByName(nameInput);
            int expectedSize = 1;

            // when
            String menuName = order.getName();
            int orderSize = order.getSize();

            // then
            assertThat(menuName).isEqualTo(nameInput);
            assertThat(orderSize).isEqualTo(expectedSize);
        }
    }

    @Nested
    @DisplayName("Order 예외")
    class OrderExceptionTest {

        @ParameterizedTest(name = "주문 입력이 [{0}]일 시 예외가 발생하는가?")
        @ValueSource(strings = {"abc", "   ab  "})
        @NullAndEmptySource
        @DisplayName("잘못된 형식의 일반 문자, null, 빈 문자, 공백 포함 문자 등을 입력받으면 예외가 발생한다.")
        void unValidOrderInputExceptionTest(final String orderInput) {
            // when & then
            assertThatThrownBy(() -> Order.from(orderInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @ParameterizedTest(name = "메뉴 이름이 [{0}]일 시 예외가 발생하는가?")
        @ValueSource(strings={"abc", "   제로콜라  ", "팝콘"})
        @NullAndEmptySource
        @DisplayName("이름의 값에 잘못된 값이 전달되면 예외가 발생한다. (없는 메뉴, null, 빈 문자, 공백 포함 문자")
        void unValidNameInputExceptionTest(final String nameInput) {
            // given
            String orderInput = nameInput + "-" + "5";

            // when & then
            assertThatThrownBy(() -> Order.from(orderInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }

        @ParameterizedTest(name = "수량 값이 [{0}]일 시 예외가 발생하는가?")
        @ValueSource(strings={"abc", "   1  ", "0", "-1", "50"})
        @NullAndEmptySource
        @DisplayName("수량의 값에 잘못된 값이 전달되면 예외가 발생한다. (일반 문자, null, 빈 문자, 공백 포함 문자, 1 미만, 20 초과)")
        void unValidSizeInputExceptionTest(final String sizeInput) {
            // given
            String orderInput = TAPAS.getName() + "-" + sizeInput;

            // when & then
            assertThatThrownBy(() -> Order.from(orderInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BAD_MENU_EXCEPTION.getMessage());
        }
    }
}
