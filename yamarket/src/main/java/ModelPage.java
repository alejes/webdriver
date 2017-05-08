import buttons.Basket;
import buttons.Comparison;
import buttons.Postpone;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class ModelPage {
    @NotNull
    private String name;
    private String photo = null;
    @NotNull
    private String shortFeatures;
    @NotNull
    private Basket basket;
    @NotNull
    private Postpone postpone;
    @NotNull
    private Comparison comparison;
    @NotNull
    private List<Tab> tabs;
    private String avgPrice = null;

    private ModelPage(@NotNull String name,
                      @NotNull String shortFeatures,
                      @NotNull Basket basket,
                      @NotNull Postpone postpone,
                      @NotNull Comparison comparison,
                      @NotNull List<Tab> tabs) {
        this.name = name;
        this.shortFeatures = shortFeatures;
        this.basket = basket;
        this.postpone = postpone;
        this.comparison = comparison;
        this.tabs = tabs;
    }

    public static ModelPage build(WebDriver driver) {
        String name = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
        String photo = driver.findElement(By.xpath("//div[contains(@class, 'n-gallery__item-wrap_active_yes')]/div/img[@class='n-gallery__image image']")).getAttribute("src");
        String about = driver.findElement(By.xpath("//ul[@class='n-product-spec-list']")).getText();

        WebElement postpone = driver.findElement(By.xpath("//a[contains(@data-bem, 'wishlist-control')]"));
        WebElement compare = driver.findElement(By.xpath("//a[contains(@data-bem, 'product-action-compare')]"));
        WebElement basket = driver.findElement(By.xpath("//a[contains(@data-bem, 'buttonOfferBuy')]"));

        WebElement product = driver.findElement(By.xpath("//li[@data-name='product']/a"));
        WebElement specification = driver.findElement(By.xpath("//li[@data-name='spec']/a"));
        WebElement price = driver.findElement(By.xpath("//li[@data-name='offers']/a"));
        WebElement map = driver.findElement(By.xpath("//li[@data-name='geo']/a"));
        WebElement reviews = driver.findElement(By.xpath("//li[@data-name='reviews']/a"));
        WebElement articles = driver.findElement(By.xpath("//li[@data-name='articles']/a"));
        WebElement forums = driver.findElement(By.xpath("//li[@data-name='forums']/a"));
        String avgPrice = driver.findElement(By.xpath("//div[@class='n-w-product-average-price__average-value']/span")).getText();

        List<Tab> tabs = Arrays.asList(new Tab(product),
                new Tab(specification),
                new Tab(price),
                new Tab(map),
                new Tab(reviews),
                new Tab(articles),
                new Tab(forums));

        ModelPage obj = new ModelPage(name, about, new Basket(basket), new Postpone(postpone), new Comparison(compare), tabs);
        obj.setAvgPrice(avgPrice);
        obj.setPhoto(photo);
        return obj;
    }

    public Tab getProduct() {
        return tabs.get(0);
    }

    public Tab getSpecification() {
        return tabs.get(1);
    }

    public Tab getPrice() {
        return tabs.get(2);
    }

    public Tab getMap() {
        return tabs.get(3);
    }

    public Tab getReviews() {
        return tabs.get(4);
    }

    public Tab getArticles() {
        return tabs.get(5);
    }

    public Tab getForums() {
        return tabs.get(6);
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    private void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    private void setPhoto(String photo) {
        this.photo = photo;
    }

    @NotNull
    public String getShortFeatures() {
        return shortFeatures;
    }

    @NotNull
    public Basket getBasket() {
        return basket;
    }

    @NotNull
    public Postpone getPostpone() {
        return postpone;
    }

    @NotNull
    public Comparison getComparison() {
        return comparison;
    }

    @NotNull
    public List<Tab> getTabs() {
        return tabs;
    }

}
