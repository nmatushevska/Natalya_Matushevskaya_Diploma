package tests;

import org.testng.ITestContext;
import utils.DriverManager;
import utils.PropertyReader;
import utils.TestListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Properties;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    private final Properties properties = PropertyReader.readFile();
    private final String url = properties.getProperty("pageURL");

    @BeforeMethod(groups = {"one","two","three"})
    public void setup(ITestContext context) {
        driver = DriverManager.getDriver(DriverManager.Browser.Chrome);
        driver.get(url);
        context.setAttribute("webDriver", driver);
        log.info("Page "+url+" was opened");
    }

    @AfterMethod(alwaysRun = true,groups = {"one","two","three"})
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("Browser is closed");
        }
    }
}