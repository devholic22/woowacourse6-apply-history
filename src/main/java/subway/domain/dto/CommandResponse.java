package subway.domain.dto;

public class CommandResponse {

    private final String command;
    private final String name;

    private CommandResponse(final String command, final String name) {
        this.command = command;
        this.name = name;
    }

    public static CommandResponse of(final String command, final String name) {
        return new CommandResponse(command, name);
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
