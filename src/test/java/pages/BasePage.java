package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

abstract class BasePage {
    WebDriver driver;
    protected final Actions actions;

    BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }
}