package christmas.model.order;

import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;

public class Order {

    private static final int MINIMUM_SIZE = 1;
    private static final int MAXIMUM_SIZE = 20;
    private static final String VALUE_SPLITTER = "-";
    private static final int NAME_INDEX = 0;
    private static final int SIZE_INDEX = 1;

    private final Menu menu;
    private final int size;

    public Order(final Menu menu, final int size) {
        this.menu = menu;
        this.size = size;
    }

    public static Order from(final String orderInput) {
        String[] orderInputValues = orderInput.split(VALUE_SPLITTER);
        String menuNameInput = orderInputValues[NAME_INDEX];
        String sizeInput = orderInputValues[SIZE_INDEX];

        Menu menu = Menu.findByName(menuNameInput);
        int size = convertToNumber(sizeInput);
        validateSizeRangeValid(size);

        return new Order(menu, size);
    }

    private static int convertToNumber(final String sizeInput) {
        try {
            return Integer.parseInt(sizeInput);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    private static void validateSizeRangeValid(final int size) {
        if (size < MINIMUM_SIZE || size > MAXIMUM_SIZE) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getSize() {
        return size;
    }

    public int calculateCost() {
        return size * menu.getCost();
    }

    public boolean isOrderType(final MenuType type) {
        String orderType = menu.getType();
        String otherType = type.getType();

        return orderType.equals(otherType);
    }
}
