package onboarding;

import java.util.HashMap;

public class Problem4 {

    private static final int A_INDEX = 97;
    private static final HashMap<String, String> DICTIONARY = new HashMap<>();

    public static String solution(String word) {
        initDictionary();

        StringBuilder builder = new StringBuilder();
        String[] tokens = word.split("");

        for (String token : tokens) {
            if (token.isBlank()) {
                builder.append(token);
                continue;
            }
            if (DICTIONARY.containsKey(token)) {
                builder.append(DICTIONARY.get(token));
                continue;
            }
            if (DICTIONARY.containsKey(token.toLowerCase())) {
                builder.append(DICTIONARY.get(token.toLowerCase()).toUpperCase());
            }
        }

        return builder.toString();
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
