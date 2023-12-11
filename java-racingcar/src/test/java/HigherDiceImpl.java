public class HigherDiceImpl implements Dice {

    private static final int NUMBER = 10;

    @Override
    public int random() {
        return NUMBER;
    }
}
