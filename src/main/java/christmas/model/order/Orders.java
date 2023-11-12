package christmas.model.order;

import static christmas.model.menu.MenuType.DRINK;
import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Orders {

    private static final int MAXIMUM_ORDERS_SIZE = 20;
    private static final String ORDER_SPLITTER = ",";

    private final List<Order> orders;

    private Orders(final List<Order> orders) {
        this.orders = orders;
    }

    public static Orders withMenuAndSize(final Menu menu, final int size) {
        List<Order> orders = IntStream.range(0, size)
                .mapToObj(index -> Order.withMenu(menu))
                .toList();
        return new Orders(orders);
    }

    public static Orders from(final String ordersInput) {
        String[] splitOrders = ordersInput.split(ORDER_SPLITTER);
        List<Order> orders = Stream.of(splitOrders)
                .map(Order::from)
                .toList();
        validateMenusNotDuplicate(orders);
        validateMenusNotOnlyDrink(orders);
        validateMenusTotalSizeIsNotOver(orders);

        return new Orders(orders);
    }

    public static Orders withOrders(final List<Order> orders) {
        return new Orders(orders);
    }

    private static void validateMenusNotDuplicate(final List<Order> orders) {
        List<Order> uniqueOrders = orders.stream()
                .distinct()
                .toList();

        if (uniqueOrders.size() != orders.size()) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    private static void validateMenusNotOnlyDrink(final List<Order> orders) {
        int drinkCount = (int) orders.stream()
                .filter(order -> order.isOrderType(DRINK))
                .count();

        if (drinkCount == orders.size()) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    private static void validateMenusTotalSizeIsNotOver(final List<Order> orders) {
        int orderSizeSum = orders.stream()
                .mapToInt(Order::getSize)
                .sum();

        if (orderSizeSum > MAXIMUM_ORDERS_SIZE) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    public int calculateTypeOrdersCount(final MenuType type) {
        return orders.stream()
                .filter(order -> order.isOrderType(type))
                .mapToInt(Order::getSize)
                .sum();
    }
    
    public int calculateTotalCost() {
        return orders.stream()
                .mapToInt(Order::calculateCost)
                .sum();
    }

    public List<Order> orders() {
        return orders;
    }
}
