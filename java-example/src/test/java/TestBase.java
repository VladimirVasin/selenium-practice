import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestBase {


    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

    @AfterAll
        public static void stop() {
        driver.quit();
        driver = null;
    }

}


