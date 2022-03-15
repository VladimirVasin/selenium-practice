import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;

public class task8Countries extends TestBase {

    @Test
    public void checkingCode() {

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> rowList = driver.findElements(By.className("row"));
        List<String> listString = new ArrayList<>();
        List<String> listChecking = new ArrayList<>();
        String nameCountry;
        for(WebElement rowCurrent:rowList) {
            nameCountry = rowCurrent.findElements(By.cssSelector("td")).get(4).getText();
            listString.add(nameCountry);
            if (!rowCurrent.findElements(By.cssSelector("td")).get(5).getText().contains("0")) {
                listChecking.add(nameCountry);

            }

        }

        List<String> listSorted = new ArrayList<>(listString);
        Collections.sort(listSorted);
        sa.assertEquals(listString,listSorted);
        checkingZones(listChecking);

        sa.assertAll();
    }

    @Test
    public void Countries() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> rowList = driver.findElements(By.className("row"));
        List<String> listString = new ArrayList<>();
        List<String> listChecking = new ArrayList<>();
        String nameCountry;
        for(WebElement rowCurrent:rowList) {
            nameCountry = rowCurrent.findElements(By.cssSelector("td")).get(4).getText();
            listString.add(nameCountry);
            if (!rowCurrent.findElements(By.cssSelector("td")).get(5).getText().contains("0")) {
                listChecking.add(nameCountry);

            }

        }

        List<String> listSorted = new ArrayList<>(listString);
        Collections.sort(listSorted);
        sa.assertEquals(listString,listSorted);

        checkingZones(listChecking);

        sa.assertAll();

    }

    public void checkingZones(List<String> listChecking) {
        WebElement countryToCheck;
        List<WebElement> listRowsZones;
        List<String> listZonesNames;
        List<String> listZonesNameSort;

        for(String currentToCheck:listChecking){
            countryToCheck = driver.findElement(By.linkText(currentToCheck));
            countryToCheck.click();
            WebElement tableZones = driver.findElement(By.className("dataTable"));

            listRowsZones = tableZones.findElements(By.cssSelector("tr"));
            listRowsZones.remove(0);
            listRowsZones.remove(listRowsZones.size()-1);

            listZonesNames = new ArrayList<>();
            for (WebElement rowZonesCurrent:listRowsZones){
                listZonesNames.add(rowZonesCurrent.findElements(By.cssSelector("td")).get(2).getText());
            }

            listZonesNameSort = new ArrayList<>(listZonesNames);
            Collections.sort(listZonesNameSort);
            sa.assertEquals(listZonesNames, listZonesNameSort);
            pressButton("Cancel");

        }

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
