package onboarding;

import java.util.Collections;
import java.util.Comparator;
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

        public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        return answer;
    }
}
