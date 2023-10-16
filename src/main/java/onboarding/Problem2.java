package onboarding;

import java.util.List;
import java.util.stream.Collectors;

public class Problem2 {
    public static String solution(String cryptogram) {
        String answer = "answer";
        return answer;
    }

    private static boolean isExistDuplicate(final List<String> tokens) {
        List<String> tokensWithoutBlank = tokens.stream()
                .filter(token -> !token.isEmpty())
                .collect(Collectors.toList());

        for (int i = 0; i < tokensWithoutBlank.size() - 1; i++) {
            if (tokensWithoutBlank.get(i).equals(tokensWithoutBlank.get(i + 1))) {
                return true;
            }
        }
        return false;
    }
}
