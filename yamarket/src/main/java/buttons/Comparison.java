package buttons;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class Comparison {
    @NotNull
    private WebElement element;

    public Comparison(@NotNull WebElement element) {

        this.element = element;
    }

    @NotNull
    public WebElement getElement() {
        return element;
    }
}
