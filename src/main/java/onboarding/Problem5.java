package onboarding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem5 {

    private static final int[] COINS = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};

    public static List<Integer> solution(int money) {
        int[] answer = new int[COINS.length];

        for (int i = 0; i < COINS.length; i++) {
            if (!isMoneyCanTakeCoin(money, i)) {
                continue;
            }
            answer[i] = money / COINS[i];
            money %= COINS[i];
        }

        return convertIntArrayToArrayList(answer);
    }

    private static ArrayList<Integer> convertIntArrayToArrayList(final int[] answer) {
        return Arrays.stream(answer)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static boolean isMoneyCanTakeCoin(final int money, final int index) {
        return money >= COINS[index];
    }
}
