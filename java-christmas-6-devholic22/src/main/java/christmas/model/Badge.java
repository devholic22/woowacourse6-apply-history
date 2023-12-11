package christmas.model;

public enum Badge {

    NOT_THING("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int cost;

    Badge(final String name, final int cost) {
        this.name = name;
        this.cost = cost;
    }

    public static Badge findByCost(final int cost) {
        if (cost < STAR.cost) {
            return NOT_THING;
        }
        if (cost < TREE.cost) {
            return STAR;
        }
        if (cost < SANTA.cost) {
            return TREE;
        }
        return SANTA;
    }

    public String getName() {
        return name;
    }
}
