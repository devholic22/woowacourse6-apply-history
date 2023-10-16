package onboarding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem2 {

    public static String solution(String cryptogram) {
        List<String> tokens = convertStringToList(cryptogram);
        boolean find = false;
        int start = 0;
        int end = 0;
        while (isExistDuplicate(tokens)) {
            for (int i = 0; i < tokens.size() - 1; i++) {
                if (!isExistDuplicate(tokens)) {
                    break;
                }
                if (!find && isBothSame(tokens.get(i), tokens.get(i + 1))) {
                    find = true;
                    start = i;
                    end = i + 1;
                } else if (find && isBothSame(tokens.get(i), tokens.get(i + 1))) {
                    end = i + 1;
                } else if (find && !isBothSame(tokens.get(i), tokens.get(i + 1))) {
                    find = false;
                    for (int j = start; j <= end; j++) {
                        tokens.remove(start);
                    }
                    start = i + 1;
                    end = i + 1;
                }
            }
        }
        return String.join("", tokens);
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
