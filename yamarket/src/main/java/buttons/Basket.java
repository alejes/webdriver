package buttons;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class Basket {
    @NotNull
    private WebElement element;

    public Basket(@NotNull WebElement element) {

        this.element = element;
    }

    @NotNull
    public WebElement getElement() {
        return element;
    }
}
