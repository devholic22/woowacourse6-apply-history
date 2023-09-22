import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<Name> names) {
        List<Car> cars = names.stream()
                .map(Car::from)
                .collect(Collectors.toList());
        return new Cars(cars);
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

    public int getCarListCount() {
        return this.cars.size();
    }
}
