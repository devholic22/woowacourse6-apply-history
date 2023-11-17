package vendingmachine.model;

public class Size {

    private static final int EACH_POP = 1;

    private final Number size;

    private Size(final Number size) {
        this.size = size;
    }

    public static Size from(final String sizeInput) {
        Number size = Number.from(sizeInput);
        return new Size(size);
    }

    public boolean isEmpty() {
        return !size.canMinus(EACH_POP);
    }

    public void pop() {
        size.minus(EACH_POP);
    }
}
