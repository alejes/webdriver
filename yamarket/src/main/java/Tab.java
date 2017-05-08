import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;


public class Tab {
    @NotNull
    private WebElement element;
    @NotNull
    private String link;

    public Tab(@NotNull WebElement element) {
        this.element = element;
        this.link = getElement().getAttribute("href");
    }

    @NotNull
    public WebElement getElement() {
        return element;
    }

    @NotNull
    public String getLink() {
        return link;
    }
}
