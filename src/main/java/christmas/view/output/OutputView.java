package christmas.view.output;

import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import java.util.List;

public interface OutputView {

    void printWelcome();
    void askRequestDay();
    void printExceptionMessage(final String message);
    void askOrderMenus();
    void printPreviewOrderAnswer(final String day);
    void printOrderedMenus(final List<OrderResponse> orders);
    void printCostBeforeDiscount(final int cost);
    void printBonusMenus(final List<OrderResponse> bonusMenus);
    void printPromotions(final List<PromotionResponse> promotions);
    void printBonusEventCost(final int cost);
    void printTotalPromotionCost(final int cost);
    void printCostAfterDiscount(final int cost);
}
