public class Dice {

    private static final int DICE_MAX_VALUE = 10;
    private static final int RANDOM_RANGE_EXPAND = 10000;

    public static int getRandomValue() {
        return (int) ((Math.random() * RANDOM_RANGE_EXPAND) % DICE_MAX_VALUE);
    }
}
