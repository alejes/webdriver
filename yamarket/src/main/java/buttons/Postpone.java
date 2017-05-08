package buttons;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class Postpone {
    @NotNull
    private WebElement element;

    public Postpone(@NotNull WebElement element) {

        this.element = element;
    }

    @NotNull
    public WebElement getElement() {
        return element;
    }
}
