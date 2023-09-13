import java.util.List;

public class Car {

    private static final int START_DISTANCE = 0;
    private static final int ACCELERATE_MINIMUM_VALUE = 4;
    private final Name name;
    private int distance;

    private Car(String name) {
        this.name = Name.from(name);
        this.distance = START_DISTANCE;
    }

    public static Car from(String name) {
        return new Car(name);
    }

    public static boolean isValueSatisfiedToAccelerate(int value) {
        if (value >= ACCELERATE_MINIMUM_VALUE) {
            return true;
        }
        return false;
    }

    public String answerDistance() {
        return "-".repeat(Math.max(0, getDistance()));
    }

    public void accelerate() {
        this.distance++;
    }

    public void answerStatus() {
        System.out.println(this.getName() + " : " + this.answerDistance());
    }

    public boolean isDistanceEqualTo(int distance) {
        return this.distance == distance;
    }

    public void joinGame(List<Car> playCars) {
        playCars.add(this);
    }

    public boolean hasName(String testName) {
        return this.name.isYourName(testName);
    }

    public String getName() {
        return name.getName();
    }

    public int getDistance() {
        return this.distance;
    }
}
