package christmas.controller;

import christmas.model.Badge;
import christmas.model.BadgeManager;
import christmas.model.BonusManager;
import christmas.model.Day;
import christmas.model.Promotion;
import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import christmas.model.order.Order;
import christmas.model.order.Orders;
import christmas.model.policy.DiscountPolicy;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;
    private final List<DiscountPolicy> policies;

    public EventController(final InputView inputView, final OutputView outputView, final List<DiscountPolicy> policies) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.policies = policies;
    }

    public void start() {
        outputView.printWelcome();
        Day requestDay = initDay();
        Orders orders = initOrders();
        printCustomerRequest(requestDay, orders);

        Orders bonusOrders = collectBonusMenusByOrderCost(orders.getTotalCost());
        List<PromotionResponse> promotions = collectPromotionsByRequest(requestDay, orders);
        int promotionCost = calculatePromotionCost(promotions);

        printBonusHistory(bonusOrders);
        outputView.printPromotions(promotions);
        outputView.printBonusEventCost(bonusOrders.getTotalCost());
        outputView.printTotalPromotionCost(promotionCost + bonusOrders.getTotalCost());
        outputView.printCostAfterDiscount(orders.getTotalCost() - promotionCost);
        printBadge(promotionCost + bonusOrders.getTotalCost());
    }

    private Day initDay() {
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

    private Orders initOrders() {
        return createInstance(Orders.class, () -> {
            outputView.askOrderMenus();
            return Orders.from(inputView.readLine());
        });
    }

    private void printCustomerRequest(final Day requestDay, final Orders orders) {
        outputView.printOrderDay(requestDay.getDay());
        printOrdersHistory(orders);
        outputView.printCostBeforeDiscount(orders.getTotalCost());
    }

    private void printOrdersHistory(final Orders menuOrders) {
        List<OrderResponse> responses = convertToOrderResponse(menuOrders);
        outputView.printOrderedMenus(responses);
    }

    private List<OrderResponse> convertToOrderResponse(final Orders orders) {
        return orders.orders()
                .stream()
                .map(order -> OrderResponse.of(order.getMenuName(), order.getSize()))
                .toList();
    }

    private Orders collectBonusMenusByOrderCost(final int cost) {
        List<Order> bonusOrderMenus = BonusManager.giveBonusOrders(cost);
        return Orders.withOrders(bonusOrderMenus);
    }

    private void printBonusHistory(final Orders bonusOrders) {
        List<OrderResponse> bonusAnswers = convertToOrderResponse(bonusOrders);
        outputView.printBonusMenus(bonusAnswers);
    }

    private List<PromotionResponse> collectPromotionsByRequest(final Day requestDay, final Orders orders) {
        List<DiscountPolicy> availablePolicies = policies.stream()
                .filter(policy -> policy.isOrdersAndDayAvailable(requestDay, orders))
                .toList();

        return availablePolicies.stream()
                .map(policy -> convertToPromotionResponse(policy, orders, requestDay))
                .toList();
    }

    private PromotionResponse convertToPromotionResponse(final DiscountPolicy discountPolicy,
                                                         final Orders orders, final Day requestDay) {
        String policyName = Promotion.findNameByPolicy(discountPolicy);
        int discount = discountPolicy.discount(orders, requestDay);
        return PromotionResponse.of(policyName, discount);
    }

    private int calculatePromotionCost(final List<PromotionResponse> promotions) {
        return promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
    }

    private void printBadge(final int cost) {
        Badge badge = BadgeManager.giveBadgeByCost(cost);
        outputView.printBadge(badge);
    }
}
