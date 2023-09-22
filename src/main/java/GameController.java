import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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
        playCars = Cars.from(createCarNames(inputReader.readLine()));

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

    private List<Name> createCarNames(String input) {
        return Name.createNameList(input);
    }

    private void saveGameCount(String input) {
        this.playDistance = Integer.parseInt(input);
    }
}
