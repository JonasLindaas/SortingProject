package Storage;

public enum Overrides {
    TEST("test1", Double.MAX_VALUE);

    public final String name;
    public final Double scoreOverride;

    private Overrides(String name, Double score) {
        this.name = name;
        scoreOverride = score;
    }

    public String getName() {
        return name;
    }

    public Double getScoreOverride() {
        return scoreOverride;
    }

    public static Overrides findOverride(String name) {
        for (Overrides o : values()) {
            if (o.name.equals(name))
                return o;
        }
        return null;
    }
}
