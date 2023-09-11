public class Dice {

    public static int getRandomValue() {
        final int DICE_MAX_VALUE = 10;
        return (int) ((Math.random() * 10000) % DICE_MAX_VALUE);
    }
}
