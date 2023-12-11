public class DiceImpl implements Dice {

    private static final int DICE_MAX_VALUE = 10;

    @Override
    public int random() {
        return (int) (Math.random() * DICE_MAX_VALUE);
    }
}
