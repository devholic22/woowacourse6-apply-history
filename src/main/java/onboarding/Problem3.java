package onboarding;

import java.util.List;

public class Problem3 {

    private static final List<Integer> CLAP_NUMBERS = List.of(3, 6, 9);

    public static int solution(int number) {
        int[] numbers = new int[number + 1];
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = numbers[i - 1] + countClap(i);
        }
        int answer = 0;
        return answer;
    }

    private static int countClap(final int number) {
        int count = 0;
        String[] tokens = String.valueOf(number).split("");

        for (String token : tokens) {
            if (CLAP_NUMBERS.contains(Integer.parseInt(token))) {
                count++;
            }
        }
        return count;
    }
}
