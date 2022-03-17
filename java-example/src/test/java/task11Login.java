import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;

public class task11Login  {

    public static WebDriver driver;

    public void setDimension() {

        driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    public static void stop() {

        driver.quit();
        driver = null;

    }

    @Test
    public void driverChrome() {

        driver = new ChromeDriver();
        setDimension();
        stop();

    }

    public void login() {

    driver.get("http://localhost:80/litecart/en/create_account");
    driver.findElement(By.name("firstname")).sendKeys("Firstname");
    driver.findElement(By.name("lastname")).sendKeys("Lastname");
    driver.findElement(By.name("address1")).sendKeys("Adress");
    driver.findElement(By.name("postcode")).sendKeys("12345");
    driver.findElement(By.name("city")).sendKeys("City");

        String uuid = UUID.randomUUID().toString().substring(0,10);
        String email = uuid + "@test.com";

    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("phone")).sendKeys("+7987654321");
    driver.findElement(By.name("password")).sendKeys("Password");
    driver.findElement(By.name("confirmed_password")).sendKeys("Password");
    driver.findElement(By.className("select2-selection__arrow")).click();
    driver.findElement(By.className("select2-search__field")).sendKeys("United States" + Keys.ENTER);
    driver.findElement(By.name("create_account")).click();
    driver.findElement(By.cssSelector("#box-account > div > ul > li:nth-child(4) > a")).click();

    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys("Password");
    driver.findElement(By.name("login")).click();

    driver.findElement(By.cssSelector("#box-account > div > ul > li:nth-child(4) > a")).click();

    }

}
