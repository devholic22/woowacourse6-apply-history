package christmas.view.exception;

public enum InputException {

    BAD_DAY_EXCEPTION("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String DEFAULT_MESSAGE = "[ERROR] ";

    private final String message;

    InputException(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return DEFAULT_MESSAGE + message;
    }
}
