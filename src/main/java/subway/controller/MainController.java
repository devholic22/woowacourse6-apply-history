package subway.controller;

import subway.domain.command.InitCommand;
import subway.domain.dto.CommandResponse;
import subway.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainController {

    private final Scanner scanner;
    private final OutputView outputView;

    public MainController(final Scanner scanner, final OutputView outputView) {
        this.scanner = scanner;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printMainScreen(collectInitCommands());
    }

    private List<CommandResponse> collectInitCommands() {
        return Arrays.stream(InitCommand.values())
                .map(command -> CommandResponse.of(command.getCommand(), command.getName()))
                .collect(Collectors.toList());
    }
}
