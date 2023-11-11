package christmas.model;

public class BadgeManager {

    private BadgeManager() {
    }

    public static Badge giveBadge(final int cost) {
        return Badge.findByCost(cost);
    }
}
