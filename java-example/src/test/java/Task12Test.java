import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.UUID;

public class Task12Test {

            public static WebDriver driver;
            public String host = "localhost";
            public String port = "80";

//    @BeforeAll
    public static void driverChrome() {

        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    @BeforeAll
    public static void edgeDriver() {

        driver = new EdgeDriver();
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
            public void Task12() {

                WebDriverWait wait = new WebDriverWait(driver,5);

                String nameProduct = "DuckCustom" + UUID.randomUUID().toString().substring(0, 6);
                driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
                login();
                driver.navigate().to("http://" + host + ":" + port + "/litecart/admin/?category_id=0&app=catalog&doc=edit_product");
                driver.findElement(By.cssSelector("label:nth-child(3) > input[type=radio]")).click();
                driver.findElement(By.name("name[en]")).sendKeys(nameProduct);
                driver.findElement(By.name("code")).sendKeys("12345");
                driver.findElement(By.cssSelector("#tab-general > table > tbody > tr:nth-child(4) > td > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > input[type=checkbox]")).click();
                driver.findElement(By.cssSelector("#tab-general > table > tbody > tr:nth-child(4) > td > div > table > tbody > tr:nth-child(1) > td:nth-child(1) > input[type=checkbox]")).click();
                driver.findElement(By.cssSelector("#tab-general > table > tbody > tr:nth-child(7) > td > div > table > tbody > tr:nth-child(4) > td:nth-child(1) > input[type=checkbox]")).click();

                driver.findElement(By.name("quantity")).clear();
                driver.findElement(By.name("quantity")).click();
                driver.findElement(By.name("quantity")).sendKeys("5");

                driver.findElement(By.name("sold_out_status_id")).click();
                driver.findElement(By.cssSelector("#tab-general > table > tbody > tr:nth-child(8) > td > table > tbody > tr > td:nth-child(4) > select > option:nth-child(3)")).click();
                String pathPicture = getClass().getClassLoader().getResource("greenDuck.png").getPath().replace("/", "\\").substring(1);
                driver.findElement(By.cssSelector("input[type=file]")).sendKeys(pathPicture);
                driver.findElement(By.name("date_valid_from")).sendKeys("18032022");
                driver.findElement(By.name("date_valid_to")).sendKeys("18032023");
                getTab("Information").click();
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("manufacturer_id"))));

                driver.findElement(By.name("manufacturer_id")).click();
                driver.findElement(By.cssSelector("#tab-information > table > tbody > tr:nth-child(1) > td > select > option:nth-child(2)")).click();
                driver.findElement(By.name("keywords")).sendKeys("123456789");
                driver.findElement(By.name("short_description[en]")).sendKeys("description_Duck");
                driver.findElement(By.className("trumbowyg-editor")).sendKeys("test description");
                driver.findElement(By.name("head_title[en]")).sendKeys("head_title_Duck");
                driver.findElement(By.name("meta_description[en]")).sendKeys("test_Duck");
                getTab("Prices").click();
                WebElement pPrice = driver.findElement(By.name("purchase_price"));
                wait.until(ExpectedConditions.elementToBeClickable(pPrice));

                pPrice.clear();
                pPrice.sendKeys("999");
                driver.findElement(By.name("purchase_price_currency_code")).click();
                driver.findElement(By.cssSelector("#tab-prices > table:nth-child(2) > tbody > tr > td > select > option:nth-child(2)")).click();
                driver.findElement(By.name("gross_prices[USD]")).clear();
                driver.findElement(By.name("gross_prices[USD]")).sendKeys("5");
                driver.findElement(By.name("gross_prices[EUR]")).clear();
                driver.findElement(By.name("gross_prices[EUR]")).sendKeys("10");
                driver.findElement(By.name("save")).click();

                driver.findElement(By.xpath("//table//*[contains(text(), '" + nameProduct + "')]")).click();

            }

            public WebElement getTab(String tabName) {
                List<WebElement> listTabs;
                listTabs = driver.findElement(By.className("index")).findElements(By.cssSelector("li"));
                for (WebElement currentTab : listTabs) {
                    if (currentTab.getText().contains(tabName)) {
                        return currentTab;
                    }
                }
                return null;
            }
        }


