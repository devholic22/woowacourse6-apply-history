package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Problem7 {

    private class Person implements Comparable<Person> {

        private int score;
        private String name;

        public Person(final int score, final String name) {
            this.score = score;
            this.name = name;
        }

        @Override
        public int compareTo(final Person other) {
            return Comparator.comparingInt((Person p) -> p.score)
                    .thenComparing(p -> p.name)
                    .reversed()
                    .compare(this, other);
        }
    }

    private static final int LEFT_INDEX = 0;
    private static final int RIGHT_INDEX = 1;

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> notRecommends = getFriendsWithUser(user, friends);
        List<String> recommends = getNotFriends(friends, notRecommends);

        List<String> answer = Collections.emptyList();
        return answer;
    }

    private static List<String> getFriendsWithUser(final String user, final List<List<String>> friends) {
        HashSet<String> result = new HashSet<>();

        for (List<String> friend : friends) {
            String leftPerson = friend.get(LEFT_INDEX);
            String rightPerson = friend.get(RIGHT_INDEX);
            if (isSamePerson(user, leftPerson) || isSamePerson(user, rightPerson)) {
                result.add(leftPerson);
                result.add(rightPerson);
            }
        }

        return new ArrayList<>(result);
    }

    private static List<String> getNotFriends(final List<List<String>> friends, final List<String> notRecommends) {
        HashSet<String> result = new HashSet<>();
        for (List<String> friend : friends) {
            String leftPerson = friend.get(LEFT_INDEX);
            String rightPerson = friend.get(RIGHT_INDEX);
            if (!notRecommends.contains(leftPerson)) {
                result.add(leftPerson);
            }
            if (!notRecommends.contains(rightPerson)) {
                result.add(rightPerson);
            }
        }

        return new ArrayList<>(result);
    }

    private static boolean isSamePerson(final String originName, final String testName) {
        return originName.equals(testName);
    }
}
