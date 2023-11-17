package vendingmachine.model.product;

import vendingmachine.model.Cost;
import vendingmachine.model.Size;

public class Product {

    private static final String WRAPPER_PREFIX = "[";
    private static final String WRAPPER_SUFFIX = "]";
    private static final String INPUT_SPLITTER = ",";
    private static final int NAME_INDEX = 0;
    private static final int COST_INDEX = 1;
    private static final int SIZE_INDEX = 2;

    private final String name;
    private final Cost cost;
    private final Size size;

    private Product(final String name, final Cost cost, final Size size) {
        this.name = name;
        this.cost = cost;
        this.size = size;
    }

    public static Product from(final String productInput) {
        String removeWrapperInput = removeWrapper(productInput);

        String[] splitInputs = removeWrapperInput.split(INPUT_SPLITTER);
        validateSplitWell(splitInputs);

        String name = splitInputs[NAME_INDEX];
        Cost cost = Cost.from(splitInputs[COST_INDEX]);
        Size size = Size.from(splitInputs[SIZE_INDEX]);

        return new Product(name, cost, size);
    }

    private static String removeWrapper(String productInput) {
        validateHaveWrapper(productInput);
        return productInput.substring(1, productInput.length() - 1);
    }

    private static void validateHaveWrapper(final String productInput) {
        if (!productInput.contains(WRAPPER_PREFIX) || !productInput.contains(WRAPPER_SUFFIX)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateSplitWell(final String[] splitInputs) {
        if (splitInputs.length != SIZE_INDEX + 1) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isYourName(final String name) {
        return this.name.equals(name);
    }

    public Cost getCost() {
        return cost;
    }
}
