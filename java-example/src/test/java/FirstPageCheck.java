import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstPageCheck extends TestBase {
    public WebElement h1 = driver.findElement(By.cssSelector(("#content > h1")));
    public WebElement h2 = driver.findElement(new By.ByCssSelector("#content > h1"));
    public static WebElement getH1(){

        FirstPageCheck firstPageCheck = new FirstPageCheck();
        WebElement h2 = firstPageCheck.h1;
        return h2;
    }

}
