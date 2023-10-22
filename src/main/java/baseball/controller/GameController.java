package baseball.controller;

import static baseball.Constant.PLAY_NUMBER_DIGIT;

import baseball.model.GameRule;
import baseball.model.RandomNumber;
import baseball.model.Referee;
import baseball.model.ResumeNumber;
import baseball.view.AskView;
import baseball.view.EndView;
import baseball.view.InputView;
import baseball.view.ResultView;
import baseball.view.StartView;

public class GameController {

    private final ResumeNumber resumeNumber;
    private final Referee referee;
    private final InputView inputView;
    private final GameRule ballRule;
    private final GameRule strikeRule;

    public GameController(final InputView inputView, final GameRule ballRule, final GameRule strikeRule) {
        this.resumeNumber = ResumeNumber.createDefault();
        this.referee = Referee.createDefault();
        this.inputView = inputView;
        this.ballRule = ballRule;
        this.strikeRule = strikeRule;
    }

    public void playBaseball() {
        StartView.welcome();
        while (resumeNumber.isWantMoreGame()) {
            play();
        }
    }

    private void play() {
        int computerNumber = RandomNumber.pickNumber();

        while (true) {
            AskView.printAskNumber();
            int userNumber = inputView.readPlayNumber();
            referee.prepareJudgement(computerNumber, userNumber);

            int ball = referee.answerResult(ballRule);
            int strike = referee.answerResult(strikeRule);
            ResultView.printResult(ball, strike);

            if (isGameEnd(strike)) {
                break;
            }
        }

        EndView.end();
        AskView.printAskResume();

        int resumeAnswer = inputView.readMoreAnswer();
        resumeNumber.updateNumber(resumeAnswer);

        referee.resetGame();
    }

    private boolean isGameEnd(final int strike) {
        return strike == PLAY_NUMBER_DIGIT.getValue();
    }
}
