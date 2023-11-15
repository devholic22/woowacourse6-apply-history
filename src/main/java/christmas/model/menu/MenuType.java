package christmas.model.menu;

public enum MenuType {

    APPETIZER("애피타이저"),
    MAIN_DISH("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private final String type;

    MenuType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
