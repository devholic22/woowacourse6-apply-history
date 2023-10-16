package onboarding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Problem6 {

    private static final int SAME_CONDITION_VALUE = 2;
    private static final int EMAIL_INDEX = 0;
    private static final int NICKNAME_INDEX = 1;
    private static final HashMap<String, String> USERS = new HashMap<>();

    public static List<String> solution(List<List<String>> forms) {

        for (List<String> form : forms) {
            String nickname = form.get(NICKNAME_INDEX);
            String email = form.get(EMAIL_INDEX);
            USERS.put(nickname, email);
        }
        List<String> answer = List.of("answer");
        return answer;
    }

    private static List<String> getAllWordsWithName(final String nickname) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < nickname.length(); i++) {
            for (int j = SAME_CONDITION_VALUE; j <= nickname.length(); j++) {
                int selectEnd = i + j;
                if (selectEnd <= nickname.length()) {
                    String word = nickname.substring(i, selectEnd);
                    words.add(word);
                }
            }
        }
        return words;
    }
}
