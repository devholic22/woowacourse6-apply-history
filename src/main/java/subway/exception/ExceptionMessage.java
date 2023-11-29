package subway.exception;

public enum ExceptionMessage {

    COMMAND_NOT_FOUND("명령어를 찾지 못했습니다."),
    STATION_NOT_FOUND("역을 찾을 수 없습니다."),
    STATION_SAME_EXCEPTION("출발역과 도착역이 동일합니다."),
    SECTION_NOT_FOUND("구간을 찾을 수 없습니다."),
    NEGATIVE_NUMBER_EXCEPTION("양수여야 합니다.");

    private static final String DEFAULT_MESSAGE = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return DEFAULT_MESSAGE + message;
    }
}
