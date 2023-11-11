package christmas.model;

public class BadgeManager {

    private BadgeManager() {
    }

    public static Badge giveBadgeByCost(final int cost) {
        return Badge.findByCost(cost);
    }
}
