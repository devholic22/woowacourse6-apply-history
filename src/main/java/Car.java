public class Car {

    private static final int START_DISTANCE = 0;
    private static final int ACCELERATE_MINIMUM_VALUE = 4;
    private static final int ACCELERATE_MAXIMUM_VALUE = 9;
    private final Name name;
    private int distance;

    private Car(Name name) {
        this.name = name;
        this.distance = START_DISTANCE;
    }

    public static Car from(Name name) {
        return new Car(name);
    }

    public static boolean isValueSatisfiedToAccelerate(int value) {
        if (value >= ACCELERATE_MINIMUM_VALUE && value <= ACCELERATE_MAXIMUM_VALUE) {
            return true;
        }
        return false;
    }

    public void accelerate() {
        this.distance++;
    }

    public void answerStatus() {
        Printer.printCarDistanceStatus(name.getName(), START_DISTANCE, distance);
    }

    public boolean isDistanceEqualTo(int distance) {
        return this.distance == distance;
    }

    public void racing(Dice dice) {
        final int randomNumber = dice.random();
        if (Car.isValueSatisfiedToAccelerate(randomNumber)) {
            this.accelerate();
        }
        this.answerStatus();
    }

    public String getName() {
        return name.getName();
    }

}
