package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

@Log4j2
public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public enum Browser {
        FireFox("firefox"),
        Chrome("chrome");

        private final String value;

        Browser(String value) {
            this.value = value;
        }
    }

    public static WebDriver getDriver(Browser browser) {

        switch (browser) {
            case FireFox:
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get().manage().window().maximize();
                break;
            case Chrome:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.get().manage().window().maximize();
                break;
            default:
                log.info("Browser was not selected.");
        }
        log.info("Selected Browser: " + browser.value);
        return driver.get();
    }
}