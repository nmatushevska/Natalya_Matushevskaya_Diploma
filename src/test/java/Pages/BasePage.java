package Pages;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.interactions.Actions;

abstract class BasePage {
    WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected final Actions actions;

    BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }
}