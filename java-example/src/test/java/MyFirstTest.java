import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {
        public static WebDriver driver;
        public static WebDriverWait wait;

        @BeforeAll
        public static void start() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);

        }

        @Test
        public void firstTest() {
            driver.get("http://www.google.com/");
            driver.findElement(By.name("q")).sendKeys("webdriver");
            driver.findElement(By.name("btnK")).click();
            wait.until(titleIs("webdriver - Поиск в Google"));

        }

        @AfterAll
        public static void stop() {
            driver.quit();
            driver = null;

        }

}





