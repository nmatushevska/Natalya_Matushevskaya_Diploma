package Tests;

import Utils.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


@Log4j2
public class BaseTest {
    WebDriver driver;

    @BeforeClass()
    public void setup() {
        driver = DriverManager.getDriver(DriverManager.Browser.Chrome);
        driver.get(System.getProperty("pageURL"));
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}