package ui_test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class YandexPage extends BasePage {
    @FindBy(xpath = "//input[@placeholder='найдётся всё']")
    WebElement searchLine;
    @FindBy(xpath = "//button[text()='Найти']")
    WebElement searchButton;
    @FindBy(xpath = "//a[@href='https://www.gismeteo.ru/']")
    WebElement gismeteoLine;

    public YandexPage(WebDriver driver) {
        super(driver);
    }


    public YandexPage findGismeteo(String findLine) {
        searchLine.click();
        searchLine.sendKeys(findLine);
        searchButton.click();
        return this;
    }

    public GismeteoPage openGismeteoPage() {

        gismeteoLine.click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));
        return new GismeteoPage(driver);
    }
}
