package christmas.view.output;

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
    void printGiftOrders(final List<OrderResponse> bonusMenus);
    void printPromotions(final List<PromotionResponse> promotions);
    void printGiftOrdersCost(final int cost);
    void printTotalBenefitCost(final int cost);
    void printCostAfterDiscount(final int cost);
    void printBadge(final String badgeName);
}
