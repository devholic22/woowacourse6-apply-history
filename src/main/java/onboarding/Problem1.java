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
        try {
            return getWinnerInBooks(pobi, crong);
        } catch (IllegalArgumentException e) {
            return ERROR;
        }
    }

    private static int getWinnerInBooks(final List<Integer> pobi, final List<Integer> crong) {
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

    private static int getResultFromBook(final List<Integer> book) throws IllegalArgumentException {
        int left = book.get(LEFT);
        int right = book.get(RIGHT);

        if (isPageError(left, right)) {
            throw new IllegalArgumentException();
        }

        int leftResult = getMaxValueFromOperations(left);
        int rightResult = getMaxValueFromOperations(right);

        return Math.max(leftResult, rightResult);
    }

    private static boolean isPageError(final int leftPage, final int rightPage) {
        return rightPage - leftPage != 1;
    }

    private static int getMaxValueFromOperations(final int number) {
        return Math.max(getDigitSum(number), getDigitMultiple(number));
    }

    private static int getDigitSum(final int number) {
        String[] digits = convertNumberToStringArray(number);
        int sum = calculateDigitSum(digits);

        return sum;
    }

    private static int calculateDigitSum(final String[] digits) {
        int sum = 0;

        for (String digit : digits) {
            sum += Integer.parseInt(digit);
        }

        return sum;
    }

    private static int getDigitMultiple(final int number) {
        String[] digits = convertNumberToStringArray(number);
        int multiple = calculateDigitMultiple(digits);

        return multiple;
    }

    private static int calculateDigitMultiple(final String[] digits) {
        int sum = 1;

        for (String digit : digits) {
            sum *= Integer.parseInt(digit);
        }

        return sum;
    }

    private static String[] convertNumberToStringArray(final int number) {
        return String.valueOf(number).split("");
    }
}
