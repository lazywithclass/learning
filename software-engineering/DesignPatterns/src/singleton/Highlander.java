package singleton;

public enum Highlander {
    INSTANCE;

    public String sayIt() {
        return "There can be only one";
    }
}
