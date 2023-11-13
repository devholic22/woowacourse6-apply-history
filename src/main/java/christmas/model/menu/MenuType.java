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

    public static MenuType findByTypeName(final String typeName) {
        for (MenuType menuType : values()) {
            if (menuType.type.equals(typeName)) {
                return menuType;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getType() {
        return type;
    }
}
