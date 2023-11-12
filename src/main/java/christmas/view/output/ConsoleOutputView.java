package christmas.view.output;

import christmas.model.Badge;
import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    private static final String SIZE_MARK = "개";
    private static final String MONEY_REGEX = "%,d";
    private static final String CURRENCY = "원";
    private static final int EMPTY_MONEY = 0;
    private static final int PROMOTION_SIGN = -1;

    @Override
    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void askRequestDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    @Override
    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    @Override
    public void askOrderMenus() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    @Override
    public void printOrderDay(final int day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @Override
    public void printOrderedMenus(final List<OrderResponse> orders) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (OrderResponse order : orders) {
            System.out.println(order.name() + " " + order.size() + SIZE_MARK);
        }
    }

    @Override
    public void printCostBeforeDiscount(final int cost) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format(MONEY_REGEX, cost) + CURRENCY);
    }

    @Override
    public void printBonusMenus(final List<OrderResponse> bonusMenus) {
        System.out.println();
        System.out.println("<증정 메뉴>");

        if (bonusMenus.isEmpty()) {
            printEmpty();
            return;
        }
        for (OrderResponse bonusMenu : bonusMenus) {
            System.out.println(bonusMenu.name() + " " + bonusMenu.size() + SIZE_MARK);
        }
    }

    private void printEmpty() {
        System.out.println("없음");
    }

    @Override
    public void printPromotions(final List<PromotionResponse> promotions) {
        System.out.println();
        System.out.println("<혜택 내역>");
        if (promotions.isEmpty()) {
            printEmpty();
            return;
        }
        for (PromotionResponse promotion : promotions) {
            System.out.println(convertPromotionAnswer(promotion));
        }
    }

    private String convertPromotionAnswer(final PromotionResponse promotion) {
        return promotion.name() + ": " + String.format(MONEY_REGEX, PROMOTION_SIGN * promotion.cost()) + CURRENCY;
    }

    @Override
    public void printBonusEventCost(final List<OrderResponse> orderResponses) {
        if (orderResponses.isEmpty()) {
            return;
        }
        int cost = orderResponses.stream()
                .mapToInt(OrderResponse::cost)
                .sum();
        System.out.println("증정 이벤트: " + String.format(MONEY_REGEX, (PROMOTION_SIGN * cost)) + CURRENCY);
    }

    @Override
    public void printTotalPromotionCost(final int cost) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.println(String.format(MONEY_REGEX, PROMOTION_SIGN * cost) + CURRENCY);
    }

    @Override
    public void printCostAfterDiscount(final int cost) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format(MONEY_REGEX, cost) + CURRENCY);
    }

    @Override
    public void printBadge(final Badge badge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getName());
    }
}
