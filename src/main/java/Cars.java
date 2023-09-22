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

    public List<String> getWinnersName(int distance) {

        return cars.stream()
                .filter(car -> car.isDistanceEqualTo(distance))
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public void racing() {
        final Dice dice = new DiceImpl();
        cars.forEach(car -> {
            car.racing(dice);
            Printer.printCarDistanceStatus(car.getName(), car.getStartDistance(), car.getDistance());
        });
    }
}
