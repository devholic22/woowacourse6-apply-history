package christmas.view.output;

import christmas.model.dto.OrderResponse;
import java.util.List;

public interface OutputView {

    void printWelcome();
    void askRequestDay();
    void printExceptionMessage(final String message);
    void askOrderMenus();
    void printPreviewOrderAnswer(final String day);
    void printOrderedMenus(final List<OrderResponse> orders);
}
