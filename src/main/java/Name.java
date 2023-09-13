import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Name> createNameList(String input) {
        String[] splitNames = input.split(NAME_SPLITTER);
        return Arrays.stream(splitNames)
                .filter(Name::validateNameLength)
                .map(Name::convertStringToName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static boolean validateNameLength(String name) {
        return name.length() >= MINIMUM_CAR_NAME_LENGTH && name.length() <= MAXIMUM_CAR_NAME_LENGTH;
    }

    private static Name convertStringToName(String name) {
        name = name.strip();
        return Name.from(name);
    }

    public String getName() {
        return this.name;
    }
}
