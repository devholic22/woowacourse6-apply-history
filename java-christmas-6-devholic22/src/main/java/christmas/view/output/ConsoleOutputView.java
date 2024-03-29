package christmas.view.output;

import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    private static final String SIZE_MARK = "개";
    private static final String MONEY_REGEX = "%,d";
    private static final String WORD_DIVIDER = " ";
    private static final String NEW_LINE = "\n";
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
        StringBuilder builder = new StringBuilder();
        builder.append("12월 ")
                .append(day)
                .append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        System.out.println(builder);
    }

    @Override
    public void printOrderedMenus(final List<OrderResponse> orders) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (OrderResponse order : orders) {
            System.out.println(convertOrderAnswer(order));
        }
    }

    private String convertOrderAnswer(final OrderResponse order) {
        StringBuilder builder = new StringBuilder();
        builder.append(order.name())
                .append(WORD_DIVIDER)
                .append(order.size())
                .append(SIZE_MARK);

        return builder.toString();
    }

    @Override
    public void printCostBeforePromotion(final int cost) {
        StringBuilder builder = new StringBuilder();
        builder.append(NEW_LINE)
                .append("<할인 전 총주문 금액>")
                .append(NEW_LINE)
                .append(String.format(MONEY_REGEX, cost))
                .append(CURRENCY);

        System.out.println(builder);
    }

    @Override
    public void printGiftOrders(final List<OrderResponse> giftOrders) {
        System.out.println();
        System.out.println("<증정 메뉴>");

        if (giftOrders.isEmpty()) {
            printEmpty();
            return;
        }
        for (OrderResponse giftOrder : giftOrders) {
            System.out.println(convertGiftOrderAnswer(giftOrder));
        }
    }

    private String convertGiftOrderAnswer(final OrderResponse giftOrder) {
        StringBuilder builder = new StringBuilder();
        builder.append(giftOrder.name())
                .append(WORD_DIVIDER)
                .append(giftOrder.size())
                .append(SIZE_MARK);

        return builder.toString();
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
        StringBuilder builder = new StringBuilder();
        builder.append(promotion.name())
                .append(": ")
                .append(String.format(MONEY_REGEX, PROMOTION_SIGN * promotion.cost()))
                .append(CURRENCY);

        return builder.toString();
    }

    @Override
    public void printGiftOrdersCost(final int cost) {
        if (cost == EMPTY_MONEY) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("증정 이벤트: ")
                .append(String.format(MONEY_REGEX, (PROMOTION_SIGN * cost)))
                .append(CURRENCY);

        System.out.println(builder);
    }

    @Override
    public void printTotalBenefitCost(final int cost) {
        StringBuilder builder = new StringBuilder();
        builder.append(NEW_LINE)
                .append("<총혜택 금액>")
                .append(NEW_LINE)
                .append(String.format(MONEY_REGEX, PROMOTION_SIGN * cost))
                .append(CURRENCY);

        System.out.println(builder);
    }

    @Override
    public void printCostAfterDiscount(final int cost) {
        StringBuilder builder = new StringBuilder();
        builder.append(NEW_LINE)
                .append("<할인 후 예상 결제 금액>")
                .append(NEW_LINE)
                .append(String.format(MONEY_REGEX, cost))
                .append(CURRENCY);

        System.out.println(builder);
    }

    @Override
    public void printBadge(final String badgeName) {
        StringBuilder builder = new StringBuilder();
        builder.append(NEW_LINE)
                .append("<12월 이벤트 배지>")
                .append(NEW_LINE)
                .append(badgeName);

        System.out.println(builder);
    }
}
