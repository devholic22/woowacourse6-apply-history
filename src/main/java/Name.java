import java.util.Arrays;
import java.util.List;

public class Name {

    private static final int MINIMUM_CAR_NAME_LENGTH = 1;
    private static final int MAXIMUM_CAR_NAME_LENGTH = 5;
    private static final String NAME_SPLITTER = ",";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        return new Name(name);
    }

    public static boolean validateNameLength(String name) {
        return name.length() >= MINIMUM_CAR_NAME_LENGTH && name.length() <= MAXIMUM_CAR_NAME_LENGTH;
    }

    public static List<String> createNameList(String input) {
        String[] splitNames = input.split(NAME_SPLITTER);
        return Arrays.asList(splitNames);
    }

    public boolean isYourName(String name) {
        return name.equals(this.name);
    }

    public String getName() {
        return this.name;
    }
}
