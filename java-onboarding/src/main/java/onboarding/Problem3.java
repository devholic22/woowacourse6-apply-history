package onboarding;

import java.util.List;

public class Problem3 {

    private static final List<Integer> CLAP_NUMBERS = List.of(3, 6, 9);

    public static int solution(int number) {
        int[] numbers = new int[number + 1];
        saveClaps(numbers);
        return numbers[number];
    }

    private static void saveClaps(final int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = numbers[i - 1] + countClap(i);
        }
    }

    private static int countClap(final int number) {
        int count = 0;
        String[] tokens = String.valueOf(number).split("");

        for (String token : tokens) {
            if (isClapNumber(token)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isClapNumber(final String token) {
        return CLAP_NUMBERS.contains(Integer.parseInt(token));
    }
}
