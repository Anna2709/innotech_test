package ui_test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GismeteoPage extends BasePage {
    @FindBy(xpath = "//input[@type='search']")
    WebElement searchLine;

    @FindBy(xpath = "//a[div/div[text()='Россия, Рязанская область, Рязань (городской округ)']]")
    WebElement menuPoint;

    @FindBy(xpath = "//div[@class=\"page-title\"]/h1")
    WebElement titleOne;

    @FindBy(xpath = "//div[@class=\"page-title\"]")
    WebElement titleTwo;

    public GismeteoPage(WebDriver driver) {
        super(driver);
    }

    public GismeteoPage findLocation(String location) {
        searchLine.click();
        searchLine.sendKeys(location);
        waitForElementVisible(menuPoint);
        menuPoint.click();
        return this;
    }

    public String getLine() {
        String text =  titleTwo.getText();
        return text;
    }
}
