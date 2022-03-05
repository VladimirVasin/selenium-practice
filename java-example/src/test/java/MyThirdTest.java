import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class MyThirdTest extends TestBase{
    SoftAssert sa = new SoftAssert();
       public void loginToAdmin(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @Test
    public void thirdTest() {

        loginToAdmin();
        checkMenu();
    }

    public void checkMenu() {
        WebElement h1;
    List<WebElement> coll = driver.findElements(cssSelector("#app- > a"));
    int size = coll.size();
    WebElement elApp;
    String h1Text;
    String appText;
    List<WebElement> listDocs;
        for (int i = 0; i < size; i++) {
        elApp = driver.findElements(cssSelector("#app- > a")).get(i);
        appText = elApp.getText();
        elApp.click();
        h1 = FirstPageCheck.getH1();

        Assert.assertTrue(h1.isDisplayed()==true );
        h1Text = h1.getText();
        //System.out.println ("H1 text: " + h1Text);
        listDocs = driver.findElements(className("docs"));
        if(listDocs.size()>0) {
            runInternalMenuClick();
        }
        else {
            sa.assertEquals(h1Text, appText);
        }
    }
        sa.assertAll();
}


    public void runInternalMenuClick () {
        List<WebElement> collInternal = driver.findElement(className("docs")).findElements(cssSelector("span"));
        int sizeInternal = collInternal.size();
        WebElement h1;
        WebElement subMenuCurrent;
        String subMenuText;
        for (int i = 0; i < sizeInternal; i++) {
            if (collInternal.size() > 0) {
                subMenuCurrent = driver.findElement(className("docs")).findElements(cssSelector("span")).get(i);
                subMenuText = subMenuCurrent.getText();
                subMenuCurrent.click();
                h1 = FirstPageCheck.getH1();
                Assert.assertTrue(h1.isDisplayed()==true );
                String h1Text = h1.getText();
                sa.assertEquals(h1Text, subMenuText);
            }

        }

   }

}




