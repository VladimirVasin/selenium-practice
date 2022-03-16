import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class task10Campaigns {

    public static WebDriver driver;
    SoftAssert sa = new SoftAssert();

    public static void stop() {

        driver.quit();
        driver = null;

    }

    public void setDimension() {

        driver.manage().window().setSize(new Dimension(1920, 1080));

    }

    @Test
    public void driverChrome() {

        driver = new ChromeDriver();
        setDimension();
        checkCampaigns();
        stop();

    }

    @Test
    public void driverEdge() {

        driver = new EdgeDriver();
        setDimension();
        checkCampaigns();
        stop();

    }

    @Test
    public void driverFirefox() {

        driver = new FirefoxDriver();
        setDimension();
        checkCampaigns();
        stop();

    }

    public void checkCampaigns() {

        driver.get("http://localhost/litecart/en/");
        List<WebElement> listContent = driver.findElement(By.cssSelector("#main > div.middle > div.content")).findElements(By.className("box"));
        List<WebElement> compaingsProduct = listContent.get(1).findElements(By.className("product"));

        WebElement productCurrent = compaingsProduct.get(0);

        String nameItem = productCurrent.findElement(By.className("name")).getText();
        WebElement regularPrice = productCurrent.findElement(By.className("regular-price"));
        WebElement campaignPrice = productCurrent.findElement(By.className("campaign-price"));
        String regularPriceValue = regularPrice.getText();
        String campaignPriceValue = campaignPrice.getText();
        String tagRegular = regularPrice.getTagName();
        sa.assertEquals(tagRegular, "s");
        String tagCampaign =  campaignPrice.getTagName();
        sa.assertEquals(tagCampaign, "strong");
        checkFontSize(regularPrice.getCssValue("font-size"), campaignPrice.getCssValue("font-size"));
        Color colorReg = Color.fromString(regularPrice.getCssValue("Color"));
        sa.assertTrue(colorReg.getColor().getRed() == colorReg.getColor().getBlue() && colorReg.getColor().getRed() == colorReg.getColor().getGreen(), "Part 1. Color should be gray");
        Color colorCampaign = Color.fromString(campaignPrice.getCssValue("Color"));
        sa.assertTrue(colorCampaign.getColor().getBlue()==0 && colorCampaign.getColor().getGreen() == 0);

        productCurrent.click();

        WebElement informationBox = driver.findElement(By.className("information"));
        WebElement regularOfDetails = informationBox.findElement(By.className("regular-price"));
        String regularPriceOfDetail = regularOfDetails.getText();
        WebElement campaignOfDetails = informationBox.findElement(By.className("campaign-price"));
        String campaignPriceOfDetail = campaignOfDetails.getText();
        String tagRegularOfDetails = regularOfDetails.getTagName();
        sa.assertEquals(tagRegularOfDetails, "s");
        String tagCampaignOfDetails =  campaignOfDetails.getTagName();
        sa.assertEquals(tagCampaignOfDetails, "strong");

        checkFontSize(regularOfDetails.getCssValue("font-size"), campaignOfDetails.getCssValue("font-size"));
        String nameOfDetails = driver.findElement(By.cssSelector("#box-product")).findElement(By.className("title")).getText();
        Color colorRegOfDetails = Color.fromString(regularOfDetails.getCssValue("Color"));
        sa.assertTrue(colorRegOfDetails.getColor().getRed() == colorRegOfDetails.getColor().getBlue() &&
                colorRegOfDetails.getColor().getRed() == colorRegOfDetails.getColor().getGreen());
        Color colorCampaignOfDetails = Color.fromString(campaignOfDetails.getCssValue("Color"));
        sa.assertTrue(colorCampaignOfDetails.getColor().getBlue()==0 && colorCampaignOfDetails.getColor().getGreen() == 0);
        sa.assertEquals(nameOfDetails, nameItem);
        sa.assertEquals(regularPriceOfDetail, regularPriceValue);
        sa.assertEquals(campaignPriceOfDetail, campaignPriceValue);

        sa.assertAll();

    }

    public void checkFontSize (String RegularPrice, String CampaignPrice) {

        double regularFontSizeDouble = Double.parseDouble(RegularPrice.substring(0, RegularPrice.length()-2));
        double campaignFontSizeDouble = Double.parseDouble(CampaignPrice.substring(0, CampaignPrice.length()-2));
        sa.assertTrue(regularFontSizeDouble < campaignFontSizeDouble, "regular<campaign");

    }

}





