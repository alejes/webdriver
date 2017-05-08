import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class YaMarketTest {
    @Test
    public void main() throws Exception {
        // File file = new File("F:\\Users\\Alexey\\Downloads\\chromedriver_win32\\chromedriver.exe");
        // System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

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

        driver.get(page.getSpecification().getLink());
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
                .getText().length() > 0);


        driver.get(page.getPrice().getLink());

        WebElement priceElement = driver.findElement(By.xpath("//div[@class='n-filter-sorter i-bem n-filter-sorter_js_inited']/a"));
        priceElement.click();

        Thread.sleep(3000);

        List<Price> prices = driver.findElements(By.xpath("//div[@class='snippet-card__price']/div[@class='price']"))
                .stream().map(it -> new Price(it.getText())).collect(Collectors.toList());
        for (int i = 1; i < prices.size(); ++i) {
            assertTrue(prices.get(i).getValue() >= prices.get(i).getValue());
        }

        //Close the browser
        driver.quit();
    }

}