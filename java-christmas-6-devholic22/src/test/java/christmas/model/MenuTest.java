package christmas.model;

import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @ParameterizedTest(name = "{0} 메뉴가 저장되어 있는가?")
    @ValueSource(strings = {
            "양송이수프", "타파스", "시저샐러드",
            "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타",
            "초코케이크", "아이스크림",
            "제로콜라", "레드와인", "샴페인"
    })
    @DisplayName("Menu 정상 조회")
    void validMenuFindTest(final String menuNameInput) {
        // when & then
        assertDoesNotThrow(() -> Menu.findByName(menuNameInput));
    }

    @ParameterizedTest(name = "값이 [{0}]일 때 예외가 발생하는가?")
    @NullAndEmptySource
    @ValueSource(strings = {"랍스타", " 레드와인 "})
    @DisplayName("없는 메뉴 이름을 입력할 시 예외가 발생한다. 공백도 금지한다.")
    void menuNotFoundExceptionTest(final String menuNameInput) {
        // when & then
        assertThatThrownBy(() -> Menu.findByName(menuNameInput)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BAD_MENU_EXCEPTION.getMessage());
    }
}
