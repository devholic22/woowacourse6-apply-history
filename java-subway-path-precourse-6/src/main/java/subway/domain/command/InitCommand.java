package subway.domain.command;

import static subway.exception.ExceptionMessage.COMMAND_NOT_FOUND;

import java.util.Arrays;

public enum InitCommand {

    FIND("1", "경로 조회"),
    END("Q", "종료");

    private final String command;
    private final String name;

    InitCommand(final String command, final String name) {
        this.command = command;
        this.name = name;
    }

    public static InitCommand findByCommandInput(final String commandInput) {
        return Arrays.stream(values())
                .filter(command -> command.isSame(commandInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_NOT_FOUND.getMessage()));
    }

    private boolean isSame(final String commandInput) {
        return command.equals(commandInput);
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
