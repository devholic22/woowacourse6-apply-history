import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private final BufferedReader inputReader;
    private Cars playCars;
    private int playDistance = 0;

    public GameController(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

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
        List<String> winners = playCars.getWinnersName(playDistance);
        Printer.alertWinners(winners);
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
