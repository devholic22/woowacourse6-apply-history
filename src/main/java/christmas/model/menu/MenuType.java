package christmas.model.menu;

import java.util.Arrays;

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
        return Arrays.stream(values())
                .filter(menuType -> menuType.isNameSame(typeName))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public String getType() {
        return type;
    }

    public boolean isNameSame(final String typeName) {
        return type.equals(typeName);
    }
}
