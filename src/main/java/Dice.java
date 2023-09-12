public class Dice {

    private static final int DICE_MAX_VALUE = 10;

    public static int getRandomValue() {
        return (int) ((Math.random() * 10000) % DICE_MAX_VALUE);
    }
}
