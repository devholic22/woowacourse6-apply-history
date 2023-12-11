package subway.controller;

import static subway.domain.command.InitCommand.END;
import static subway.domain.command.PathCommand.BACK;

import subway.domain.PathManager;
import subway.domain.Section;
import subway.domain.Station;
import subway.domain.command.InitCommand;
import subway.domain.command.PathCommand;
import subway.domain.dto.CommandResponse;
import subway.domain.repository.SectionRepository;
import subway.domain.repository.StationRepository;
import subway.view.OutputView;
import java.util.Arrays;
import java.util.Collections;
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
            InitCommand initCommand = receiveInitCommand();
            if (initCommand == END) {
                return;
            }
            PathCommand pathCommand = receivePathCommand();
            if (pathCommand == BACK) {
                continue;
            }
            List<String> pathStations = calculatePathWithCommand(pathCommand);
            if (pathStations.isEmpty()) {
                continue;
            }
            printResult(pathStations);
        }
    }

    private InitCommand receiveInitCommand() {
        return createInstance(() -> {
            outputView.printMainScreen(collectInitCommands());
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
        return createInstance(() -> {
            outputView.printPathCommands(collectPathCommands());
            outputView.askCommand();
            return PathCommand.findByCommandInput(scanner.next());
        });
    }

    private List<CommandResponse> collectPathCommands() {
        return Arrays.stream(PathCommand.values())
                .map(command -> CommandResponse.of(command.getCommand(), command.getName()))
                .collect(Collectors.toList());
    }

    private List<String> calculatePathWithCommand(PathCommand pathCommand) {
        if (pathCommand == BACK) {
            return Collections.emptyList();
        }

        PathManager pathManager = PathManager.createDefault();
        try {
            Station startStation = receiveStartStation();
            Station endStation = receiveEndStation(startStation);
            return pathManager.findPath(startStation, endStation, pathCommand);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            pathCommand = receivePathCommand();
            return calculatePathWithCommand(pathCommand);
        }
    }

    private Station receiveStartStation() {
        outputView.askStartStation();
        return StationRepository.findByName(scanner.next());
    }

    private Station receiveEndStation(final Station startStation) {
        outputView.askEndStation();
        Station endStation = StationRepository.findByName(scanner.next());
        StationRepository.validateIsBothNotSame(startStation, endStation);

        return endStation;
    }

    private int calculateTotalTime(final List<String> stations) {
        int result = 0;
        for (int index = 0; index < stations.size() - 1; index++) {
            Section section = SectionRepository.findByStartAndEnd(stations.get(index), stations.get(index + 1));
            result += section.getTime();
        }
        return result;
    }

    private int calculateTotalDistance(final List<String> stations) {
        int result = 0;
        for (int index = 0; index < stations.size() - 1; index++) {
            Section section = SectionRepository.findByStartAndEnd(stations.get(index), stations.get(index + 1));
            result += section.getDistance();
        }
        return result;
    }

    private void printResult(final List<String> stations) {
        int distance = calculateTotalDistance(stations);
        int time = calculateTotalTime(stations);
        outputView.printResult(stations, distance, time);
    }
}
