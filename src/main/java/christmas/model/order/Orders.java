package christmas.model.order;

import static christmas.model.menu.MenuType.DRINK;
import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;

import christmas.model.menu.MenuType;
import java.util.List;
import java.util.stream.Stream;

public class Orders {

    private static final String ORDER_SPLITTER = ",";

    private final List<Order> orders;

    public Orders(final List<Order> orders) {
        this.orders = orders;
    }

    private static Orders from(final String ordersInput) {
        String[] splitOrders = ordersInput.split(ORDER_SPLITTER);
        List<Order> orders = Stream.of(splitOrders)
                .map(Order::from)
                .toList();
        validateMenusNotDuplicate(orders);
        validateMenusNotOnlyDrink(orders);

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

    public List<Order> findByType(final MenuType type) {
        return orders.stream()
                .filter(order -> order.isOrderType(type))
                .toList();
    }
    
    public int getTotalCost() {
        return orders.stream()
                .mapToInt(Order::calculateCost)
                .sum();
    }
}
