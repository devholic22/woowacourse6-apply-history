public class DiceMovableImpl implements Dice {

    private static final int NUMBER = 4;

    @Override
    public int random() {
        return NUMBER;
    }
}
