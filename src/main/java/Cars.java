import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public boolean hasNotWinnerDistance(int distance) {
        return cars.stream()
                .noneMatch(car -> car.isDistanceEqualTo(distance));
    }

    public void getWinnersName(List<String> winners, int distance) {
        List<String> winnerNames = cars.stream()
                .filter(car -> car.isDistanceEqualTo(distance))
                .map(Car::getName)
                .collect(Collectors.toList());

        winners.addAll(winnerNames);
    }

    public void racing() {
        final Dice dice = new DiceImpl();
        cars.forEach(car -> car.racing(dice));
    }
}
