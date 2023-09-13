import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private List<Car> playCars;
    private int playDistance = 0;

    private void init() {
        playCars = new ArrayList<>();
    }

    private void close() throws IOException {
        inputReader.close();
    }

    public void play() throws IOException {
        init();

        Printer.askCarNames();
        createCars(inputReader.readLine());

        Printer.askGameCount();
        saveGameCount(inputReader.readLine());

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
        return Name.validateNameLength(name) &&
                !isUsedName(name);
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
