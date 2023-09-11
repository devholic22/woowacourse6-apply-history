import java.util.List;

public class Car {

    private final String name;
    private int distance;

    private Car(String name) {
        this.name = name;
        this.distance = 0;
    }

    public static Car of(String name) {
        return new Car(name);
    }

    public static boolean isValueSatisfiedToAccelerate(int value) {
        final int ACCELERATE_MINIMUM_VALUE = 4;
        return value >= ACCELERATE_MINIMUM_VALUE;
    }

    public String getName() {
        return this.name;
    }

    public int getDistance() {
        return this.distance;
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
        return getDistance() == distance;
    }

    public void joinGame(List<Car> playCars) {
        playCars.add(this);
    }
}
