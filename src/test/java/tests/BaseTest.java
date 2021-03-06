package tests;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.DriverManager;
import utils.TestListener;


@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;

    @BeforeMethod(groups = {"one", "two"})
    public void setup(ITestContext context) {
        driver = DriverManager.getDriver(DriverManager.Browser.Chrome);
        context.setAttribute("webDriver", driver);
    }

    @AfterMethod(alwaysRun = true, groups = {"one", "two"})
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("Browser is closed");
        }
    }
}