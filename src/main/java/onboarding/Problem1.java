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
        int pobiValue = getResultFromBook(pobi);
        int crongValue = getResultFromBook(crong);

        if (pobiValue > crongValue) {
            return POBI_WIN;
        }
        if (pobiValue < crongValue) {
            return CRONG_WIN;
        }
        return DRAW;
    }

    private static int getResultFromBook(List<Integer> book) {
        int left = book.get(LEFT);
        int right = book.get(RIGHT);

        int leftResult = getMaxValueFromOperations(left);
        int rightResult = getMaxValueFromOperations(right);

        return Math.max(leftResult, rightResult);
    }

    private static int getMaxValueFromOperations(int number) {
        return Math.max(calculateDigitSum(number), calculateDigitMultiply(number));
    }

    private static int calculateDigitSum(int number) {
        int sum = 0;
        String[] digits = convertNumberToStringArray(number);

        for (String digit : digits) {
            sum += Integer.parseInt(digit);
        }

        return sum;
    }

    private static int calculateDigitMultiply(int number) {
        int sum = 1;
        String[] digits = convertNumberToStringArray(number);

        for (String digit : digits) {
            sum *= Integer.parseInt(digit);
        }

        return sum;
    }

    private static String[] convertNumberToStringArray(int number) {
        return String.valueOf(number).split("");
    }
}
