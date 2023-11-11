package christmas.model.order;

import static christmas.view.exception.InputException.BAD_MENU_EXCEPTION;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.util.Objects;

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

    public static Order createByName(final String nameInput) {
        Menu menu = Menu.findByName(nameInput);
        return new Order(menu, MINIMUM_SIZE);
    }

    public static Order from(final String orderInput) {
        validateIsInputNotNull(orderInput);
        validateIsInputHasValueSplitter(orderInput);

        String[] orderInputValues = orderInput.split(VALUE_SPLITTER);
        validateIsInputSplittedCorrect(orderInputValues);

        String menuNameInput = orderInputValues[NAME_INDEX];
        String sizeInput = orderInputValues[SIZE_INDEX];

        Menu menu = Menu.findByName(menuNameInput);
        int size = convertToNumber(sizeInput);
        validateSizeRangeValid(size);

        return new Order(menu, size);
    }

    private static void validateIsInputNotNull(final String orderInput) {
        if (orderInput == null) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    private static void validateIsInputHasValueSplitter(final String orderInput) {
        if (!orderInput.contains(VALUE_SPLITTER)) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
    }

    private static void validateIsInputSplittedCorrect(final String[] splittedInput) {
        if (splittedInput.length <= SIZE_INDEX) {
            throw new IllegalArgumentException(BAD_MENU_EXCEPTION.getMessage());
        }
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

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Order order = (Order) other;
        return menu == order.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
