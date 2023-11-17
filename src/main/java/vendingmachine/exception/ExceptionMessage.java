package vendingmachine.exception;

public enum ExceptionMessage {

    NUMBER_FORMAT_EXCEPTION("숫자로 변환하는 과정에서 오류가 발생했습니다."),
    NOT_POSITIVE_NUMBER("숫자는 양수여야 합니다."),
    DIVIDE_EXCEPTION("나누어떨어지지 않습니다."),
    NOT_ENOUGH_NUMBER("최소 요구 수를 충족하지 않았습니다.");

    private static final String DEFAULT_MESSAGE = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return DEFAULT_MESSAGE + message;
    }
}
