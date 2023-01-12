package ui_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ui_test.pages.BasePage;
import ui_test.pages.YandexPage;

import java.util.concurrent.TimeUnit;

public class TestGismeteo {

    public static WebDriver driver;
    public static final String BASE_URL = "https://ya.ru/";
    public static final String GISMETEO = "gismeteo";
    public static final String LOCATION = "Рязань";
    public static final String EXPECTED_LINE = "Погода в Рязани сегодня";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //BasePage basePage = new BasePage(driver);
        BasePage.getCurrentSession().set(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testGismeteo() {
        driver.get(BASE_URL);
        String actualLine = new YandexPage(driver).findGismeteo(GISMETEO).openGismeteoPage().findLocation(LOCATION).getLine();
        System.out.println(actualLine);
        Assert.assertEquals(actualLine, EXPECTED_LINE, "Line 'Погода в Рязани сегодня' has not been found");
    }
}
