package onboarding;

import java.util.HashMap;

public class Problem4 {

    private static final int A_INDEX = 97;
    private static final StringBuilder builder = new StringBuilder();
    private static final HashMap<String, String> DICTIONARY = new HashMap<>();

    public static String solution(String word) {
        initDictionary();

        String[] tokens = getTokensFromWord(word);

        for (String token : tokens) {
            if (isBlank(token)) {
                addValueIntoBuilder(token);
                continue;
            }
            if (isDictionaryHasKey(token)) {
                addValueIntoBuilder(getReverse(token));
                continue;
            }
            if (isDictionaryHasKey(getLowerCase(token))) {
                addValueIntoBuilder(getReverseUpperCase(token));
            }
        }

        return builder.toString();
    }

    private static boolean isBlank(final String token) {
        return token.isBlank();
    }

    private static String getLowerCase(final String token) {
        return token.toLowerCase();
    }

    private static String getReverse(final String token) {
        return DICTIONARY.get(token);
    }

    private static boolean isDictionaryHasKey(final String token) {
        return DICTIONARY.containsKey(token);
    }

    private static String getReverseUpperCase(final String token) {
        return DICTIONARY.get(token.toLowerCase()).toUpperCase();
    }

    private static void addValueIntoBuilder(final String value) {
        builder.append(value);
    }

    private static String[] getTokensFromWord(final String word) {
        return word.split("");
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
