package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Problem7 {

    private static class Person implements Comparable<Person> {

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
    private static final int FRIEND_SCORE = 10;
    private static final int LIMIT_SIZE = 5;
    private static final List<Person> PERSONS = new ArrayList<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> notRecommends = getFriendsWithUser(user, friends);
        List<String> recommends = getNotFriends(friends, notRecommends);
        recommends.addAll(getNotFriendsFromVisitors(visitors, notRecommends));

        for (String recommendName : recommends) {
            int duplicatePerson = getDuplicateFriends(user, recommendName, friends);
            PERSONS.add(new Person(duplicatePerson * FRIEND_SCORE, recommendName));
        }

        for (Person person : PERSONS) {
            int visitCount = countNameInVisited(person.name, visitors);
            person.score = Math.max(visitCount, person.score);
        }

        List<Person> resultPerson = PERSONS.stream()
                .filter(person -> person.score > 0)
                .limit(LIMIT_SIZE)
                .collect(Collectors.toList());


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

    private static List<String> getNotFriendsFromVisitors(final List<String> visitors, final List<String> notCommends) {
        HashSet<String> result = new HashSet<>();
        
        for (String visitor : visitors) {
            if (notCommends.contains(visitor)) {
                continue;
            }
            result.add(visitor);
        }
        return new ArrayList<>(result);
    }

    private static int getDuplicateFriends(final String originName, final String testName, final List<List<String>> friends) {
        HashSet<String> originNameFriends = getFriendsWithName(originName, friends);
        HashSet<String> testNameFriends = getFriendsWithName(testName, friends);
        originNameFriends.retainAll(testNameFriends);

        return originNameFriends.size();
    }

    private static HashSet<String> getFriendsWithName(final String name, final List<List<String>> friends) {
        HashSet<String> result = new HashSet<>();
        for (List<String> friend : friends) {
            String leftPerson = friend.get(LEFT_INDEX);
            String rightPerson = friend.get(RIGHT_INDEX);
            if (isSamePerson(name, leftPerson)) {
                result.add(rightPerson);
                continue;
            }
            if (isSamePerson(name, rightPerson)) {
                result.add(leftPerson);
            }
        }

        return result;
    }

    private static int countNameInVisited(final String name, final List<String> visitors) {
        int count = 0;
        for (String visitor : visitors) {
            if (isSamePerson(name, visitor)) {
                count++;
            }
        }
        return count;
    }
    private static boolean isSamePerson(final String originName, final String testName) {
        return originName.equals(testName);
    }
}
