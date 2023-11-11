package christmas.controller;

import christmas.model.Badge;
import christmas.model.BadgeManager;
import christmas.model.BonusManager;
import christmas.model.Customer;
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

    public EventController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Customer customer = inviteCustomer();
        printCustomerRequest(customer);

        Orders bonusOrders = collectBonusByOrders(customer.getOrders());
        List<PromotionResponse> promotions = collectPromotionsByRequest(customer.getDay(), customer.getOrders());

        printPromotionAndBonusHistory(promotions, bonusOrders);
        printTotalCostAfterPromotion(promotions, customer.getOrders());
        printBadgeWithCost(promotions, bonusOrders);
    }

    private Customer inviteCustomer() {
        outputView.printWelcome();

        Day requestDay = receiveDay();
        return createInstance(Customer.class, () -> {
            Orders requestOrders = receiveOrders();
            return Customer.of(requestDay, requestOrders);
        });
    }

    private Day receiveDay() {
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

    private void printCustomerRequest(final Customer customer) {
        printOrderDay(customer.getDay());
        printOrdersHistory(customer.getOrders());

        outputView.printCostBeforeDiscount(customer.calculateTotalCost());
    }

    private void printOrderDay(final Day day) {
        outputView.printOrderDay(day.getDay());
    }

    private void printOrdersHistory(final Orders orders) {
        List<OrderResponse> responses = convertToOrderResponse(orders);
        outputView.printOrderedMenus(responses);
    }

    private List<OrderResponse> convertToOrderResponse(final Orders orders) {
        return orders.orders()
                .stream()
                .map(order -> OrderResponse.of(order.getMenuName(), order.getSize()))
                .toList();
    }

    private Orders collectBonusByOrders(final Orders orders) {
        List<Order> bonusOrderMenus = BonusManager.giveBonusOrdersForCost(orders.calculateTotalCost());
        return Orders.withOrders(bonusOrderMenus);
    }

    private List<PromotionResponse> collectPromotionsByRequest(final Day requestDay, final Orders orders) {
        List<DiscountPolicy> availablePolicies = Promotion.collectPoliciesByRequest(requestDay, orders);

        return availablePolicies.stream()
                .map(policy -> convertToPromotionResponse(policy, requestDay, orders))
                .toList();
    }

    private PromotionResponse convertToPromotionResponse(final DiscountPolicy discountPolicy,
                                                         final Day requestDay, final Orders orders) {
        String policyName = Promotion.findNameByPolicy(discountPolicy);
        int discount = discountPolicy.discount(requestDay, orders);
        return PromotionResponse.of(policyName, discount);
    }

    private void printPromotionAndBonusHistory(final List<PromotionResponse> promotions, final Orders bonusOrders) {
        printBonusHistory(bonusOrders);
        outputView.printPromotions(promotions);
        outputView.printBonusEventCost(bonusOrders.calculateTotalCost());
        printTotalPromotionCost(promotions, bonusOrders);
    }

    private void printBonusHistory(final Orders bonusOrders) {
        List<OrderResponse> bonusAnswers = convertToOrderResponse(bonusOrders);
        outputView.printBonusMenus(bonusAnswers);
    }

    private void printTotalPromotionCost(final List<PromotionResponse> promotions, final Orders bonusOrders) {
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        outputView.printTotalPromotionCost(promotionCost + bonusOrders.calculateTotalCost());
    }

    private void printTotalCostAfterPromotion(final List<PromotionResponse> promotions, final Orders orders) {
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        outputView.printCostAfterDiscount(orders.calculateTotalCost() - promotionCost);
    }

    private void printBadgeWithCost(final List<PromotionResponse> promotions, final Orders bonusOrders) {
        int promotionCost = promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
        Badge badge = BadgeManager.giveBadgeByCost(promotionCost + bonusOrders.calculateTotalCost());

        outputView.printBadge(badge);
    }
}
