import java.util.ArrayList;
import java.util.List;

public class Printer {

    private static final String DISTANCE_MARK = "-";

    public static void askCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public static void askGameCount() {
        System.out.println("시도할 횟수는 몇회인가요?");
    }

    public static void alertResultMessage() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public static void alertWinners(Cars playCars, int playDistance) {
        List<String> winners = new ArrayList<>();
        playCars.getWinnersName(winners, playDistance);
        if (isOnlyOneWinner(winners)) {
            System.out.print(winners.get(0));
            return;
        }
        chainingWinnerNames(winners);
    }

    public static void alertEndMessage() {
        System.out.println("가 최종 우승했습니다.");
    }

    public static void printCarDistanceStatus(String name, int start, int end) {
        System.out.println(name + " : " + DISTANCE_MARK.repeat(Math.max(start, end)));
    }

    private static void chainingWinnerNames(List<String> names) {
        for (int i = 0; i < names.size() - 1; i++) {
            System.out.print(names.get(i) + ", ");
        }
        System.out.print(names.get(names.size() - 1));
    }

    private static boolean isOnlyOneWinner(List<String> winners) {
        return winners.size() == 1;
    }
}
