package subway.domain.command;

import static subway.exception.ExceptionMessage.COMMAND_NOT_FOUND;

import java.util.Arrays;

public enum PathCommand {

    MINIMUM_DISTANCE("1", "최단 거리"),
    MINIMUM_TIME("2", "최소 시간"),
    BACK("B", "돌아가기");

    private final String command;
    private final String name;

    PathCommand(final String command, final String name) {
        this.command = command;
        this.name = name;
    }

    public static PathCommand findByCommandInput(final String commandInput) {
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
