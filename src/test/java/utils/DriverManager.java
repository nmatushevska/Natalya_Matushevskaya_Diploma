package utils;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver = null;

    public enum Browser {
        FireFox("firefox"),
        Chrome("chrome");

        private final String value;

        Browser(String value) {
            this.value = value;
        }
    }

    public static WebDriver getDriver(Browser browser) {
        Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);
        switch (browser) {
            case FireFox:
                FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            case Chrome:
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            default:
                LOGGER.info("Browser was not selected.");
        }
        LOGGER.info("Selected Browser: " + browser.value);
        return driver;
    }
}