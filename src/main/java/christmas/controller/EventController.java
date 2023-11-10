package christmas.controller;

import christmas.model.Day;
import christmas.model.dto.DayResponse;
import christmas.model.dto.OrderResponse;
import christmas.model.order.Order;
import christmas.model.order.Orders;
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
        outputView.printWelcome();
        Day requestDay = initDay();
        Orders orders = initOrders();
        printDayHistory(requestDay);
        printOrdersHistory(orders);
        printTotalCostBeforeDiscount(orders);
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
        List<OrderResponse> responses = menuOrders.orders()
                .stream()
                .map(order -> OrderResponse.of(order.getMenuName(), order.getSize()))
                .toList();

        outputView.printOrderedMenus(responses);
    }

    private void printTotalCostBeforeDiscount(final Orders menuOrders) {
        int cost = (int) menuOrders.orders()
                .stream()
                .mapToInt(Order::calculateCost)
                .sum();

        outputView.printCostBeforeDiscount(cost);
    }
}
