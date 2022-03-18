import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

public class Task14Windows {

    public static WebDriver driver;
    SoftAssert sa = new SoftAssert();

    @BeforeAll
    public static void driverChrome() {

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    @AfterAll
    public static void stop() {
        driver.quit();
        driver = null;
    }

    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void task14() {
        String host = "localhost";
        String port = "80";
        driver.get("http://" + host + ":" + port + "/litecart/admin/");
        login();
        driver.navigate().to("http://localhost:" + port + "/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("#content > form > table > tbody > tr:nth-child(2) > td:nth-child(5) > a")).click();
        String urlBefore = getBrowserUrl();
        String urlAfter;
        List<WebElement> listLinks = driver.findElements(By.cssSelector("td > a > i"));
        int sizeList = listLinks.size();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        for (int i = 0; i<sizeList; i++) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            driver.findElements(By.cssSelector("td > a > i")).get(i).click();

            String newWindow = wait.until(windows(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);

            urlAfter = getBrowserUrl();
            sa.assertFalse(urlAfter.contains(urlBefore));
        }
    }
    public ExpectedCondition<String> windows (Set<String>oldWindows){
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver){
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return  handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    public static String getBrowserUrl() {

        String currentUrl = driver.getCurrentUrl();
        return  currentUrl;

    }

}
