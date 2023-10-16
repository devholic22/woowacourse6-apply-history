package onboarding;

import java.util.List;

class Problem1 {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int POBI_WIN = 1;
    private static final int CRONG_WIN = 0;
    private static final int DRAW = 0;
    private static final int ERROR = -1;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;
        return answer;
    }

    private static int calculateDigitSum(int number) {
        int sum = 0;
        String[] digits = String.valueOf(number).split("");

        for (String digit : digits) {
            sum += Integer.parseInt(digit);
        }

        return sum;
    }
}
