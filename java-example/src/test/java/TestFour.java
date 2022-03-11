import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TestFour extends TestBase {

    @Test
    public void testFour () {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> checkSticker;
        List<WebElement> ducksList = driver.findElements(By.className("product"));
        for (WebElement ducksSticker : ducksList) {
            checkSticker = ducksSticker.findElements(By.className("sticker"));
            sa.assertEquals(checkSticker.size(), 1);
            System.out.println("sticker:" + checkSticker.get(0).getText());
        }
        sa.assertAll();
    }

}