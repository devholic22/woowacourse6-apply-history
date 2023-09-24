import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final Dice dice = new DiceImpl();
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final String nameInput) {
        List<Name> names = Name.createNameList(nameInput);
        List<Car> cars = names.stream()
                .map(Car::createDefault)
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
        cars.forEach(car -> {
            car.racing(dice);
        });
    }

    public HashMap<String, Integer> getAllCarsDistanceStatus() {
        HashMap<String, Integer> carsInfo = new HashMap<>();
        cars.forEach(car -> {
            carsInfo.put(car.getName(), car.getDistance());
        });
        return carsInfo;
    }

    public int getCarListCount() {
        return this.cars.size();
    }
}
