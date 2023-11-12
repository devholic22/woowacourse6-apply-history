package christmas.controller;

import christmas.model.Badge;
import christmas.model.BadgeManager;
import christmas.model.Day;
import christmas.model.GiftManager;
import christmas.model.Promotion;
import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import christmas.model.order.Orders;
import christmas.model.policy.DiscountPolicy;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Day visitDay = receiveDay();
        Orders requestOrders = receiveOrders();
        printRequestHistory(visitDay, requestOrders);

        List<OrderResponse> giftResponses = collectGiftsByOrders(requestOrders);
        List<PromotionResponse> promotions = collectPromotionsByRequest(visitDay, requestOrders);

        printPromotionAndBonusHistory(promotions, giftResponses);
        printTotalCostAfterPromotion(promotions, requestOrders);
        printBadgeWithCost(promotions, giftResponses);
    }

    private Day receiveDay() {
        outputView.printWelcome();

        return createInstance(Day.class, () -> {
            outputView.askRequestDay();
            return Day.from(inputView.readLine());
        });
    }

    private <T> T createInstance(final Class<T> classType, final Supplier<T> creator) {
        T created = null;
        while (created == null) {
            created = tryGetInstance(creator, created);
        }
        return created;
    }

    private <T> T tryGetInstance(final Supplier<T> creator, T created) {
        try {
            created = creator.get();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
        }
        return created;
    }

    private Orders receiveOrders() {
        return createInstance(Orders.class, () -> {
            outputView.askOrderMenus();
            return Orders.from(inputView.readLine());
        });
    }

    private void printRequestHistory(final Day visitDay, final Orders requestOrders) {
        List<OrderResponse> orderResponses = requestOrders.orders()
                .stream()
                .map(order -> OrderResponse.of(order.getMenuName(), order.getSize(), order.calculateCost()))
                .toList();

        outputView.printOrderDay(visitDay.getDay());
        outputView.printOrderedMenus(orderResponses);
        outputView.printCostBeforeDiscount(requestOrders.calculateTotalCost());
    }

    private List<OrderResponse> collectGiftsByOrders(final Orders requestOrders) {
        int totalCost = requestOrders.calculateTotalCost();
        Orders giftOrders = GiftManager.giveGiftsForCost(totalCost);

        return giftOrders.orders()
                .stream()
                .map(giftOrder -> OrderResponse.of(giftOrder.getMenuName(), giftOrder.getSize(), giftOrder.calculateCost()))
                .toList();
    }

    private List<PromotionResponse> collectPromotionsByRequest(final Day visitDay, final Orders requestOrders) {
        List<DiscountPolicy> availablePolicies = Promotion.collectPoliciesByRequest(visitDay, requestOrders);

        return availablePolicies.stream()
                .map(policy -> convertPromotionAnswer(policy, visitDay, requestOrders))
                .toList();
    }

    private PromotionResponse convertPromotionAnswer(final DiscountPolicy policy, final Day day, final Orders orders) {
        String policyName = Promotion.findNameByPolicy(policy);
        int discount = policy.discount(day, orders);

        return PromotionResponse.of(policyName, discount);
    }

    private void printPromotionAndBonusHistory(final List<PromotionResponse> promotions, final List<OrderResponse> giftOrders) {
        outputView.printBonusMenus(giftOrders);
        outputView.printPromotions(promotions);
        outputView.printBonusEventCost(giftOrders);
        printTotalPromotionCost(promotions, giftOrders);
    }

    private void printTotalPromotionCost(final List<PromotionResponse> promotions, final List<OrderResponse> giftOrders) {
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        int giftCost = giftOrders.stream()
                .mapToInt(OrderResponse::cost)
                .sum();
        outputView.printTotalPromotionCost(promotionCost + giftCost);
    }

    private void printTotalCostAfterPromotion(final List<PromotionResponse> promotions, final Orders orders) {
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        outputView.printCostAfterDiscount(orders.calculateTotalCost() - promotionCost);
    }

    private void printBadgeWithCost(final List<PromotionResponse> promotions, final List<OrderResponse> giftOrders) {
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        int giftCost = giftOrders.stream()
                .mapToInt(OrderResponse::cost)
                .sum();
        Badge badge = BadgeManager.giveBadgeByCost(promotionCost + giftCost);

        outputView.printBadge(badge);
    }
}
