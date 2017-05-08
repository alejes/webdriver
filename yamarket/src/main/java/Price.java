import org.jetbrains.annotations.NotNull;


public class Price {
    @NotNull
    private Integer value = 0;

    public Price(@NotNull Integer value) {
        this.value = value;
    }

    public Price(@NotNull String value) {
        this.value = Integer.valueOf(value.replace(" руб.", "").replaceAll(" ", ""));
    }

    @NotNull
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}
