import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static BufferedReader br;
    private static List<Car> playCars;
    private int playDistance = 0;

    private static void init() {
        br = new BufferedReader(new InputStreamReader(System.in));
        playCars = new ArrayList<>();
    }

    private static void close() throws IOException {
        br.close();
    }

    public void play() throws IOException {
        init();

        Printer.askCarNames();
        createCars(br.readLine());

        Printer.askGameCount();
        saveGameCount(br.readLine());

        Printer.alertResultMessage();
        while (isWinnerNotExist())
            race();
        Printer.alertWinners(playCars, playDistance);
        Printer.alertEndMessage();

        close();
    }

    private void createCars(String input) {
        String[] carNames = input.split(",");
        for (String carName : carNames) {
            String stripCarName = carName.strip();
            if (!validateName(carName)) {
                continue;
            }
            Car playCar = Car.from(stripCarName);
            playCar.joinGame(playCars);
        }
    }

    private boolean isWinnerNotExist() {
        return playCars.stream().noneMatch(car -> car.isDistanceEqualTo(playDistance));
    }

    private void saveGameCount(String input) {
        this.playDistance = Integer.parseInt(input);
    }

    private boolean validateName(String name) {
        final int MINIMUM_CAR_NAME_LENGTH = 1;
        final int MAXIMUM_CAR_NAME_LENGTH = 5;

        return !isNameLengthLowerThan(name, MINIMUM_CAR_NAME_LENGTH) &&
                !isNameLengthLongerThan(name, MAXIMUM_CAR_NAME_LENGTH) &&
                !isUsedName(name);
    }

    private boolean isNameLengthLongerThan(String name, int length) {
        return name.length() > length;
    }

    private boolean isNameLengthLowerThan(String name, int length) {
        return name.length() < length;
    }

    private boolean isUsedName(String name) {
        for (Car car : playCars) {
            if (car.hasName(name))
                return true;
        }
        return false;
    }

    private void race() {
        playCars.forEach(car -> {
            int value = Dice.getRandomValue();
            if (Car.isValueSatisfiedToAccelerate(value)) {
                car.accelerate();
            }
            car.answerStatus();
        });
        System.out.println();
    }
}
