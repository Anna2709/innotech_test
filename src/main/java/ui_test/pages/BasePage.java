package ui_test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private static ThreadLocal<WebDriver> currentSession = new ThreadLocal<WebDriver>();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static ThreadLocal getCurrentSession() {
        return currentSession;
    }

    protected void waitForElementVisible(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofMillis(1000L)).until(ExpectedConditions.visibilityOf(webElement));
    }
}
