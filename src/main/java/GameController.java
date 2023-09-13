import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private Cars playCars;
    private int playDistance = 0;

    private void close() throws IOException {
        inputReader.close();
    }

    public void play() throws IOException {
        Printer.askCarNames();
        playCars = new Cars(createCars(inputReader.readLine()));

        Printer.askGameCount();
        saveGameCount(inputReader.readLine());

        Printer.alertResultMessage();
        while (playCars.hasNotWinnerDistance(playDistance)) {
            playCars.racing();
            System.out.println();
        }

        Printer.alertWinners(playCars, playDistance);
        Printer.alertEndMessage();

        close();
    }

    private List<Car> createCars(String input) {
        List<Name> candidateNames = Name.createNameList(input);
        return candidateNames.stream()
                .map(Car::from)
                .collect(Collectors.toList());
    }

    private void saveGameCount(String input) {
        this.playDistance = Integer.parseInt(input);
    }
}
