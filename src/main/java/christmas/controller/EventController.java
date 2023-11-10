package christmas.controller;

import christmas.model.Day;
import christmas.model.dto.DayResponse;
import christmas.model.order.Orders;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
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
}
