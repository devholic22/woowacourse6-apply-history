public class Car {

    private static final int START_DISTANCE = 0;
    private static final int ACCELERATE_MINIMUM_VALUE = 4;
    private static final int ACCELERATE_MAXIMUM_VALUE = 9;

    private final Name name;
    private int distance;

    private Car(final Name name, final int distance) {
        this.name = name;
        this.distance = distance;
    }

    public static Car createDefault(Name name) {
        return new Car(name, START_DISTANCE);
    }

    public boolean isValueSatisfiedToAccelerate(int value) {
        if (value >= ACCELERATE_MINIMUM_VALUE && value <= ACCELERATE_MAXIMUM_VALUE) {
            return true;
        }
        return false;
    }

    public void accelerate() {
        this.distance++;
    }

    public boolean isDistanceEqualTo(int distance) {
        return this.distance == distance;
    }

    public void racing(Dice dice) {
        final int randomNumber = dice.random();
        if (isValueSatisfiedToAccelerate(randomNumber)) {
            this.accelerate();
        }
    }

    public String getName() {
        return name.getName();
    }

    public int getStartDistance() {
        return START_DISTANCE;
    }

    public int getDistance() {
        return this.distance;
    }
}
