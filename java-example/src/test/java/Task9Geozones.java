import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task9Geozones extends TestBase {

    @Test
    public void checkingGeozones() {

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> rowsList =  driver.findElements(By.className("row"));
        List<String> sortedListCountries;
        int rows = rowsList.size();
        WebElement rowCurrent;

        for (int i = 0;  i < rows; ++i) {
            rowCurrent = driver.findElements(By.className("row")).get(i);
            rowCurrent.findElement(By.cssSelector("a")).click();
            List<WebElement> listCountriesWebEl = driver.findElements(By.xpath("//table[@id='table-zones']//td[3]//select[contains(@name,'zones[')]//option[@selected]"));
            List<String> listCountries = new ArrayList<>();

            for (WebElement countryCurrent:listCountriesWebEl) {
                listCountries.add(countryCurrent.getText());

            }

            sortedListCountries = new ArrayList<>(listCountries);
            Collections.sort(sortedListCountries);

            sa.assertEquals(listCountries, sortedListCountries);
            pressButton("Cancel");

        }

        sa.assertAll();

    }

    public void pressButton(String buttonName){
        List<WebElement> listButtons = driver.findElement(By.className("button-set")).findElements(By.cssSelector("button"));
        for(WebElement button:listButtons){
            if(button.getText().contains(buttonName)) {
                button.click();
                return;

            }

        }

    }

}


