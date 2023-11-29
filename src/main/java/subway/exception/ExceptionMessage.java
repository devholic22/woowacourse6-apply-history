package subway.exception;

public enum ExceptionMessage {

    COMMAND_NOT_FOUND("명령어를 찾지 못했습니다.");

    private static final String DEFAULT_MESSAGE = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return DEFAULT_MESSAGE + message;
    }
}
