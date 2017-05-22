import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class YaMarketTest {
    @Test
    public void main() throws Exception {
        File file = new File("/home/user/AU/Testing/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        // Create a new instance of the Chrome driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = new ChromeDriver();

        // And now use this to visit Yandex Market
        String url = "https://market.yandex.ru/product/1720217048?hid=91491&track=tabs";
        driver.get(url);

        ModelPage page = ModelPage.build(driver);
        assertNotNull(page.getAvgPrice());
        assertNotNull(page.getPhoto());

        /*driver.get(page.getSpecification().getLink());
        assertEquals("Подробные характеристики", driver.findElement(By.xpath("//h2[@class='title title_size_22 n-product-spec-wrap__title']"))
                .getText());

        driver.get(page.getArticles().getLink());
        assertEquals("Видеообзоры", driver.findElement(By.xpath("//h3[@class='n-product-video__title']"))
                .getText());

        driver.get(page.getForums().getLink());
        assertEquals("Обсуждения", driver.findElement(By.xpath("//h2[@class='title title_size_22']"))
                .getText());


        driver.get(page.getReviews().getLink());
        assertEquals("Все отзывы", driver.findElement(By.xpath("//h2[@class='title title_size_22']"))
                .getText());

        driver.get(page.getMap().getLink());
        assert (driver.findElement(By.xpath("//div[@id='product-geo']"))
                .getText().length() > 0);*/


        driver.get(page.getPrice().getLink());

        WebElement priceElement = driver.findElement(By.xpath("//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']/a"));
        priceElement.click();


        List<Price> prices = new ArrayList<>();


        do {
            new WebDriverWait(driver, 10)
                    .until((ExpectedCondition<Boolean>) d ->
                            d != null && d.findElement(
                                    By.xpath("//div[@class='preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja']/div")) != null);

            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='preloadable__preloader preloadable__preloader_visibility_visible preloadable__paranja']/div")));
            //Thread.sleep(3000);

            prices.addAll(driver.findElements(By.xpath("//div[@class='snippet-card__price']/div[@class='price']"))
                    .stream().map(it -> new Price(it.getText())).collect(Collectors.toList()));

            WebElement element = driver.findElement(By.xpath("//a[contains(@class, 'n-pager__button-next i-bem')]/span[@class='button__text']"));
            System.out.println(element);
            System.out.println(element.getText());

            //Actions build = new Actions(driver);
            //build.moveToElement(element).click().build().perform();

            //((JavascriptExecutor) driver).executeScript("window.scroll(0, " + element.getLocation().getY() + ")");
            System.out.println(element.getAttribute("href"));
            element.click();

            break;
        }
        while (true);


        for (int i = 1; i < prices.size(); ++i) {
            assertTrue(prices.get(i).getValue() >= prices.get(i).getValue());
        }

        //Close the browser
        driver.quit();
    }

}