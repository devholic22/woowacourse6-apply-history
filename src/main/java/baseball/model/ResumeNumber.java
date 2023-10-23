package baseball.model;

import static baseball.Constant.END_WANT;
import static baseball.Constant.PLAY_WANT;

public class ResumeNumber {

    private static final int RESUME_ANSWER_LENGTH = 1;

    private int resumeNumber;

    private ResumeNumber(final int number) {
        this.resumeNumber = number;
    }

    public static ResumeNumber createDefault() {
        return new ResumeNumber(PLAY_WANT.getValue());
    }

    public void updateNumber(final String answer) {
        validateAnswer(answer);
        this.resumeNumber = Integer.parseInt(answer);
    }

    private void validateAnswer(final String answer) {
        validateAnswerLength(answer);
        validateAnswerValue(answer);
    }

    private void validateAnswerLength(final String answer) {
        if (answer.length() != RESUME_ANSWER_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    private void validateAnswerValue(final String answer) {
        if ((!answer.equals(PLAY_WANT.toString())) && (!answer.equals(END_WANT.toString()))) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isWantMoreGame() {
        return resumeNumber == PLAY_WANT.getValue();
    }
}
