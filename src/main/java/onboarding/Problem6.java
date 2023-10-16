package onboarding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Problem6 {

    private static final int SAME_CONDITION_VALUE = 2;
    private static final int EMAIL_INDEX = 0;
    private static final int NICKNAME_INDEX = 1;
    private static final HashSet<String> answer = new HashSet<>();
    private static final HashMap<String, String> USERS = new HashMap<>();
    private static final HashMap<String, List<String>> WORDS = new HashMap<>();

    public static List<String> solution(List<List<String>> forms) {

        for (List<String> form : forms) {
            String nickname = form.get(NICKNAME_INDEX);
            String email = form.get(EMAIL_INDEX);
            USERS.put(nickname, email);
            WORDS.put(nickname, getAllWordsWithName(nickname));
            appendEmailIfSameWordExist(nickname);
        }
        return new ArrayList<>(new TreeSet<>(answer));
    }

    private static List<String> getAllWordsWithName(final String nickname) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < nickname.length(); i++) {
            saveNicknameWordToWordsStartFromIndex(nickname, i, words);
        }
        return words;
    }

    private static void saveNicknameWordToWordsStartFromIndex(final String nickname, final int index, final List<String> words) {
        for (int j = SAME_CONDITION_VALUE; j <= nickname.length(); j++) {
            int selectEnd = index + j;
            if (selectEnd <= nickname.length()) {
                String word = nickname.substring(index, selectEnd);
                words.add(word);
            }
        }
    }

    private static void appendEmailIfSameWordExist(final String nickname) {
        List<String> wordsByNickname = getAllWordsWithName(nickname);
        for (String word : wordsByNickname) {
            appendEmailIfSameWordInWords(nickname, word);
        }
    }

    private static void appendEmailIfSameWordInWords(final String nickname, final String word) {
        for (String otherName : WORDS.keySet()) {
            if (isNicknameEqualToOther(nickname, otherName)) {
                continue;
            }
            if (isAlreadySavedOtherNameWord(word, otherName)) {
                appendEmailByName(otherName);
                appendEmailByName(nickname);
            }
        }
    }

    private static void appendEmailByName(final String name) {
        answer.add(USERS.get(name));
    }

    private static boolean isAlreadySavedOtherNameWord(final String word, final String otherName) {
        return WORDS.get(otherName).contains(word);
    }

    private static boolean isNicknameEqualToOther(final String nickname, final String otherName) {
        return otherName.equals(nickname);
    }
}
