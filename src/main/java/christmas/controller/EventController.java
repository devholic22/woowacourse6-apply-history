package christmas.controller;

import christmas.model.Badge;
import christmas.model.BadgeManager;
import christmas.model.BonusManager;
import christmas.model.Day;
import christmas.model.Promotion;
import christmas.model.dto.DayResponse;
import christmas.model.dto.PromotionResponse;
import christmas.model.dto.OrderResponse;
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
        printDayHistory(requestDay);
        printOrdersHistory(orders);
        outputView.printCostBeforeDiscount(orders.getTotalCost());
        List<Order> bonusOrders = BonusManager.giveBonusOrders(orders.getTotalCost());
        printBonusOrdersHistory(bonusOrders);
        List<PromotionResponse> promotions = collectAvailablePromotions(orders, requestDay);
        outputView.printPromotions(promotions);
        int bonusCost = bonusOrders.stream()
                .mapToInt(Order::calculateCost)
                .sum();
        outputView.printBonusEventCost(bonusCost);
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        outputView.printTotalPromotionCost(promotionCost + bonusCost);
        outputView.printCostAfterDiscount(orders.getTotalCost() - promotionCost);
        collectBadgeAndPrintByCost(promotionCost + bonusCost);
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

    private void printDayHistory(final Day day) {
        DayResponse dayResponse = DayResponse.from(day.getDay());
        outputView.printPreviewOrderAnswer(dayResponse.day());
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

    private void printBonusOrdersHistory(final List<Order> bonusOrder) {
        List<OrderResponse> responses = bonusOrder.stream()
                .map(order -> OrderResponse.of(order.getMenuName(), order.getSize()))
                .toList();

        outputView.printBonusMenus(responses);
    }

    private List<PromotionResponse> collectAvailablePromotions(final Orders orders, final Day requestDay) {
        List<DiscountPolicy> availablePolicies = policies.stream()
                .filter(policy -> policy.isOrdersAndDayAvailable(orders, requestDay))
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

    private void collectBadgeAndPrintByCost(final int cost) {
        Badge badge = BadgeManager.giveBadgeByCost(cost);
        outputView.printBadge(badge);
    }
}
