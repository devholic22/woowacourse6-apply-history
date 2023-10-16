package onboarding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem2 {
    public static String solution(String cryptogram) {
        String answer = "answer";
        return answer;
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
}
