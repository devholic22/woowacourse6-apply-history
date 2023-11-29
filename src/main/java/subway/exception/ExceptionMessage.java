package subway.exception;

public enum ExceptionMessage {

    COMMAND_NOT_FOUND("명령어를 찾지 못했습니다."),
    STATION_NOT_FOUND("역을 찾을 수 없습니다."),
    STATION_SAME_EXCEPTION("출발역과 도착역이 동일합니다."),
    SECTION_NOT_FOUND("구간을 찾을 수 없습니다."),
    NEGATIVE_NUMBER_EXCEPTION("양수여야 합니다."),
    CANNOT_FIND_EXCEPTION("출발역과 도착역이 연결되어 있지 않아 경로 탐색이 불가능합니다."),
    OTHER_EXCEPTION("알 수 없는 오류가 발생했습니다.");

    private static final String DEFAULT_MESSAGE = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return DEFAULT_MESSAGE + message;
    }
}
