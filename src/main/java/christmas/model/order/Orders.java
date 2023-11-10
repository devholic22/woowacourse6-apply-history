package christmas.model.order;

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

        return new Orders(orders);
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
