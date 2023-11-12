package christmas.model.manager;

import christmas.model.Badge;

public class BadgeManager {

    private BadgeManager() {
    }

    public static Badge giveBadgeByCost(final int cost) {
        return Badge.findByCost(cost);
    }
}
