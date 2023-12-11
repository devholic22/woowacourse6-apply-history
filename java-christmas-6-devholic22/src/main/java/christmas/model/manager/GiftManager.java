package christmas.model.manager;

import static christmas.model.menu.Menu.CHAMPAGNE;

import christmas.model.order.Orders;

public class GiftManager {

    private static final int MINIMUM_COST = 120_000;
    private static final int NOT_GIFT = 0;
    private static final int GIFT_SIZE = 1;

    private GiftManager() {

    }

    public static Orders giveGiftsForCost(final int cost) {
        if (cost < MINIMUM_COST) {
            return Orders.withMenuAndSize(CHAMPAGNE, NOT_GIFT);
        }
        return Orders.withMenuAndSize(CHAMPAGNE, GIFT_SIZE);
    }
}
