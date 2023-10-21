package baseball.controller;

import static baseball.Constants.PLAY_NUMBER_DIGIT;

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
        printGameWelcome();
        while (isGameCanPlay()) {
            play();
        }
    }

    private static void printGameWelcome() {
        StartView.welcome();
    }

    private boolean isGameCanPlay() {
        return resumeNumber.isWantMoreGame();
    }

    private void play() {
        int computerNumber = selectNewNumber();

        while (true) {
            askNumberInput();

            int userNumber = receiveUserNumber();

            int ball = Umpire.countBall(computerNumber, userNumber);
            int strike = Umpire.countStrike(computerNumber, userNumber);

            ResultView.printResult(ball, strike);

            if (isStrikeEqualToGoal(strike)) {
                break;
            }
        }
        printGameEnd();
        askResumeInput();
        updateResumeNumber();
    }

    private int selectNewNumber() {
        return NumberFactory.createNumber();
    }

    private static void askNumberInput() {
        AskController.askNumberInput();
    }

    private static int receiveUserNumber() {
        return InputController.receiveUserNumber();
    }

    private boolean isStrikeEqualToGoal(final int strike) {
        return strike == PLAY_NUMBER_DIGIT;
    }

    private static void printGameEnd() {
        EndView.end();
    }

    private void updateResumeNumber() {
        resumeNumber.updateNumber(receiveResumeNumber());
    }

    private int receiveResumeNumber() {
        return InputController.receiveResumeNumber();
    }

    private static void askResumeInput() {
        AskController.askResumeInput();
    }
}
