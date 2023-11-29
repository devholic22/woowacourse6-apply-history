package subway.controller;

import static subway.domain.command.InitCommand.END;
import static subway.domain.command.PathCommand.BACK;

import subway.domain.PathManager;
import subway.domain.Station;
import subway.domain.command.InitCommand;
import subway.domain.command.PathCommand;
import subway.domain.dto.CommandResponse;
import subway.domain.repository.StationRepository;
import subway.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MainController {

    private final Scanner scanner;
    private final OutputView outputView;

    public MainController(final Scanner scanner, final OutputView outputView) {
        this.scanner = scanner;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            outputView.printMainScreen(collectInitCommands());
            InitCommand initCommand = receiveInitCommand();
            if (initCommand == END) {
                return;
            }
            PathCommand pathCommand = receivePathCommand();
            if (pathCommand == BACK) {
                continue;
            }
            Station startStation = receiveStartStation();
            Station endStation = receiveEndStation(startStation);
            PathManager pathManager = PathManager.createDefault();
            pathManager.findPath(startStation, endStation, pathCommand);
            /*
            if (pathCommand == MINIMUM_DISTANCE) {

            }
            if (pathCommand == MINIMUM_TIME) {

            }
             */
        }
    }

    private InitCommand receiveInitCommand() {
        return createInstance(() -> {
            outputView.askCommand();
            return InitCommand.findByCommandInput(scanner.next());
        });
    }

    private <T> T createInstance(final Supplier<T> creator) {
        T created = null;
        while (created == null) {
            created = tryGetInstance(creator, created);
        }
        return created;
    }

    private <T> T tryGetInstance(final Supplier<T> creator, T created) {
        try {
            created = creator.get();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
        }
        return created;
    }

    private List<CommandResponse> collectInitCommands() {
        return Arrays.stream(InitCommand.values())
                .map(command -> CommandResponse.of(command.getCommand(), command.getName()))
                .collect(Collectors.toList());
    }

    private PathCommand receivePathCommand() {
        outputView.printPathCommands(collectPathCommands());
        return createInstance(() -> {
            outputView.askCommand();
            return PathCommand.findByCommandInput(scanner.next());
        });
    }

    private List<CommandResponse> collectPathCommands() {
        return Arrays.stream(PathCommand.values())
                .map(command -> CommandResponse.of(command.getCommand(), command.getName()))
                .collect(Collectors.toList());
    }

    private Station receiveStartStation() {
        return createInstance(() -> {
            outputView.askStartStation();
            return StationRepository.findByName(scanner.next());
        });
    }

    private Station receiveEndStation(final Station startStation) {
        return createInstance(() -> {
            outputView.askEndStation();
            Station endStation = StationRepository.findByName(scanner.next());
            StationRepository.validateIsBothNotSame(startStation, endStation);

            return endStation;
        });
    }
}
