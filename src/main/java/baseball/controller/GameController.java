package baseball.controller;

import static baseball.Constants.END_WANT;
import static baseball.Constants.PLAY_NUMBER_DIGIT;
import static baseball.Constants.PLAY_WANT;

import baseball.Umpire;
import baseball.factory.NumberFactory;
import baseball.model.ResumeNumber;
import baseball.view.EndView;
import baseball.view.ResultView;
import baseball.view.StartView;

public class GameController {

    private final ResumeNumber resumeNumber;

    public GameController() {
        this.resumeNumber = ResumeNumber.createDefault();
    }

    public void playBaseball() {
        StartView.welcome();
        while (resumeNumber.isWantMoreGame()) {
            play();
        }
    }

    private void play() {
        boolean playWant = true;
        int computerNumber = selectNewNumber();

        while (playWant) {
            AskController.askNumberInput();

            int userNumber = InputController.receiveUserNumberWidthLength(PLAY_NUMBER_DIGIT);

            int ball = Umpire.countBall(computerNumber, userNumber);
            int strike = Umpire.countStrike(computerNumber, userNumber);

            ResultView.printResult(ball, strike);

            if (isStrikeEqualToGoal(strike)) {
                playWant = false;
            }
        }
        EndView.end(PLAY_NUMBER_DIGIT);
        AskController.askResumeInputWithOption(PLAY_WANT, END_WANT);
        resumeNumber.updateNumber(InputController.receiveResumeNumberWithOption(PLAY_WANT, END_WANT));
    }

    private boolean isStrikeEqualToGoal(final int strike) {
        return strike == PLAY_NUMBER_DIGIT;
    }

    private int selectNewNumber() {
        return NumberFactory.pickNumberWithLength(PLAY_NUMBER_DIGIT);
    }
}
