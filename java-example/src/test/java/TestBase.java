import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static WebDriverWait wait;
    SoftAssert sa = new SoftAssert();

    @BeforeAll
    public static void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

    }

    @AfterAll
        public static void stop() {
        driver.quit();
        driver = null;

    }

}



