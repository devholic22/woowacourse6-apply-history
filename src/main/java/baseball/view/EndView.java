package baseball.view;

import static baseball.Constant.PLAY_NUMBER_DIGIT;

public class EndView {

    public static void end() {
        System.out.println(PLAY_NUMBER_DIGIT.getValue() + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
