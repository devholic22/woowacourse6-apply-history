package christmas.model;

import static christmas.model.menu.Menu.CHAMPAGNE;

import christmas.model.order.Order;
import java.util.Collections;
import java.util.List;

public class BonusManager {

    private static final int MINIMUM_COST = 120_000;

    private BonusManager() {

    }

    public static List<Order> giveBonusOrdersForCost(final int cost) {
        if (cost < MINIMUM_COST) {
            return Collections.emptyList();
        }
        Order bonusOrder = Order.createByName(CHAMPAGNE.getName());
        return List.of(bonusOrder);
    }
}
