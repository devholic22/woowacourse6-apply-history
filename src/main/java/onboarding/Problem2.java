package onboarding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem2 {

    public static String solution(String cryptogram) {
        List<String> tokens = convertStringToList(cryptogram);
        while (isExistDuplicate(tokens)) {
            removeDuplicateToken(tokens);
        }
        return convertListToString(tokens);
    }

    private static void removeDuplicateToken(final List<String> tokens) {
        boolean find = false;
        int start = 0;
        int end = 0;

        for (int i = 0; i < tokens.size() - 1; i++) {
            if (isOnlyRemainTwoAndBothSame(tokens)) {
                clearList(tokens);
                break;
            }
            if (!find && isBothSame(tokens.get(i), tokens.get(i + 1))) {
                find = changeFind(find);
                start = i;
                end = i + 1;
            } else if (find && isBothSame(tokens.get(i), tokens.get(i + 1))) {
                end = i + 1;
            } else if (find && !isBothSame(tokens.get(i), tokens.get(i + 1))) {
                find = changeFind(find);
                changeValueToBlankFromStartToEnd(tokens, start, end);
                start = i + 1;
                end = i + 1;
            }
        }
    }

    private static boolean changeFind(boolean find) {
        return !find;
    }

    private static void clearList(final List<String> tokens) {
        tokens.clear();
    }

    private static String convertListToString(final List<String> tokens) {
        return String.join("", tokens);
    }

    private static void changeValueToBlankFromStartToEnd(final List<String> tokens, final int start, final int end) {
        for (int j = start; j <= end; j++) {
            tokens.remove(start);
        }
    }

    private static boolean isOnlyRemainTwoAndBothSame(final List<String> tokens) {
        return tokens.size() == 2 && isBothSame(tokens.get(0), tokens.get(1));
    }

    private static boolean isBothSame(final String origin, final String test) {
        return origin.equals(test);
    }

    private static boolean isExistDuplicate(final List<String> tokens) {
        List<String> tokensWithoutBlank = getValuesWithoutBlank(tokens);

        return IntStream.range(0, tokensWithoutBlank.size() - 1)
                .anyMatch(i -> tokensWithoutBlank.get(i).equals(tokensWithoutBlank.get(i + 1)));
    }

    private static List<String> getValuesWithoutBlank(final List<String> tokens) {
        return tokens.stream()
                .filter(token -> !token.isEmpty())
                .collect(Collectors.toList());
    }

    private static List<String> convertStringToList(final String value) {
        return Arrays.stream(value.split(""))
                .collect(Collectors.toList());
    }
}
