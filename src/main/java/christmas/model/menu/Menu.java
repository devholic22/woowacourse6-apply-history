package christmas.model.menu;

import static christmas.model.menu.MenuType.APPETIZER;
import static christmas.model.menu.MenuType.DESSERT;
import static christmas.model.menu.MenuType.DRINK;
import static christmas.model.menu.MenuType.MAIN_DISH;
import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;

public enum Menu {

    SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    SALAD(APPETIZER, "시저샐러드", 8_000),
    STEAK(MAIN_DISH, "티본스테이크", 55_000),
    BARBEQUE(MAIN_DISH, "바비큐립", 54_000),
    SEA_PASTA(MAIN_DISH, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN_DISH, "크리스마스파스타", 25_000),
    CAKE(DESSERT, "초코케이크", 15_000),
    ICECREAM(DESSERT, "아이스크림", 5_000),
    COKE(DRINK, "제로콜라", 3_000),
    WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000);

    private final MenuType type;
    private final String name;
    private final int cost;

    Menu(final MenuType type, final String name, final int cost) {
        this.type = type;
        this.name = name;
        this.cost = cost;
    }

    public static Menu findByName(final String name) {
        for (Menu menu : values()) {
            if (name.equals(menu.name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
    }

    public String getType() {
        return type.getType();
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}
