package christmas.model;

import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuTest {

    @Test
    @DisplayName("Menu 정상 조회 테스트")
    void validMenuFindTest() {
        // given
        String menuNameInput = "제로콜라";

        // when & then
        assertDoesNotThrow(() -> Menu.findByName(menuNameInput));
    }

    @Test
    @DisplayName("없는 메뉴 이름을 입력할 시 예외가 발생한다.")
    void menuNotFoundExceptionTest() {
        // given
        String menuNameInput = "랍스타";

        // when & then
        assertThatThrownBy(() -> Menu.findByName(menuNameInput)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BAD_MENU_EXCEPTION.getMessage());
    }
}
