package onboarding;

import java.util.HashMap;

public class Problem4 {

    private static final int A_INDEX = 97;
    private static final HashMap<String, String> DICTIONARY = new HashMap<>();

    public static String solution(String word) {
        initDictionary();
        String answer = "";
        return answer;
    }

    private static void initDictionary() {
        for (char character = 'a'; character <= 'z'; character++) {
            DICTIONARY.put(String.valueOf(character), String.valueOf(createReverseAlphabet(character)));
        }
    }

    private static char createReverseAlphabet(final char character) {
        return (char) ('z' - (character - A_INDEX));
    }
}
