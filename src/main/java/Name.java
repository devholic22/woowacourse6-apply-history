public class Name {

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        return new Name(name);
    }

    public boolean isYourName(String name) {
        return name.equals(this.name);
    }

    public String getName() {
        return this.name;
    }
}
