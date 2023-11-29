package subway.domain.command;

public enum InitCommand {

    FIND("1", "경로 조회"),
    END("Q", "종료");

    private final String command;
    private final String name;

    InitCommand(final String command, final String name) {
        this.command = command;
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
