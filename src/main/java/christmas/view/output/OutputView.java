package christmas.view.output;

import christmas.model.Badge;
import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import java.util.List;

public interface OutputView {

    void printWelcome();
    void askRequestDay();
    void printExceptionMessage(final String message);
    void askOrderMenus();
    void printOrderDay(final int day);
    void printOrderedMenus(final List<OrderResponse> orders);
    void printCostBeforeDiscount(final int cost);
    void printBonusMenus(final List<OrderResponse> bonusMenus);
    void printPromotions(final List<PromotionResponse> promotions);
    void printBonusEventCost(final List<OrderResponse> orderResponses);
    void printTotalPromotionCost(final int cost);
    void printCostAfterDiscount(final int cost);
    void printBadge(final Badge badge);
}
