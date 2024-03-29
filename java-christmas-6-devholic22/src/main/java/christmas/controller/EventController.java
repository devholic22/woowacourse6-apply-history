package christmas.controller;

import christmas.model.Badge;
import christmas.model.Day;
import christmas.model.Promotion;
import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import christmas.model.manager.CostManager;
import christmas.model.manager.GiftManager;
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

        printBenefitHistory(promotions, giftResponses);
        printTotalCostAfterPromotion(promotions, requestOrders);
        printBadgeWithCost(promotions, giftResponses);
    }

    private Day receiveDay() {
        outputView.printWelcome();
        return createInstance(() -> {
            outputView.askRequestDay();
            return Day.from(inputView.readLine());
        });
    }

    private <T> T createInstance(final Supplier<T> creator) {
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
        return createInstance(() -> {
            outputView.askOrderMenus();
            return Orders.from(inputView.readLine());
        });
    }

    private void printRequestHistory(final Day visitDay, final Orders requestOrders) {
        List<OrderResponse> orderResponses = requestOrders.getOrders()
                .stream()
                .map(order -> OrderResponse.of(order.getName(), order.getSize(), order.calculateCost()))
                .toList();

        outputView.printOrderDay(visitDay.getDay());
        outputView.printOrderedMenus(orderResponses);
        outputView.printCostBeforePromotion(requestOrders.calculateTotalCost());
    }

    private List<OrderResponse> collectGiftsByOrders(final Orders requestOrders) {
        int totalCost = requestOrders.calculateTotalCost();
        Orders giftOrders = GiftManager.giveGiftsForCost(totalCost);

        return giftOrders.getOrders()
                .stream()
                .map(giftOrder -> OrderResponse.of(giftOrder.getName(), giftOrder.getSize(), giftOrder.calculateCost()))
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

    private void printBenefitHistory(final List<PromotionResponse> promotions, final List<OrderResponse> giftOrders) {
        outputView.printGiftOrders(giftOrders);
        outputView.printPromotions(promotions);

        printGiftOrdersCost(giftOrders);
        printTotalBenefitCost(promotions, giftOrders);
    }

    private void printGiftOrdersCost(final List<OrderResponse> giftOrders) {
        int giftCost = CostManager.calculateGiftOrdersCost(giftOrders);
        outputView.printGiftOrdersCost(giftCost);
    }

    private void printTotalBenefitCost(final List<PromotionResponse> promotions, final List<OrderResponse> giftOrders) {
        int promotionCost = CostManager.calculatePromotionCost(promotions);
        int giftCost = CostManager.calculateGiftOrdersCost(giftOrders);

        outputView.printTotalBenefitCost(promotionCost + giftCost);
    }

    private void printTotalCostAfterPromotion(final List<PromotionResponse> promotions, final Orders orders) {
        int promotionCost = CostManager.calculatePromotionCost(promotions);
        outputView.printCostAfterDiscount(orders.calculateTotalCost() - promotionCost);
    }

    private void printBadgeWithCost(final List<PromotionResponse> promotions, final List<OrderResponse> giftOrders) {
        int promotionCost = CostManager.calculatePromotionCost(promotions);
        int giftCost = CostManager.calculateGiftOrdersCost(giftOrders);
        Badge badge = Badge.findByCost(promotionCost + giftCost);

        outputView.printBadge(badge.getName());
    }
}
