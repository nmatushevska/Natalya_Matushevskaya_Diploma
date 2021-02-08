package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class BasePage {
    WebDriver driver;
    protected final Actions actions;
    @FindBy(xpath = "//a[@class='m-header__user']")
    WebElement accountDropDownMenu;

    BasePage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    public void openAccountTab(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        actions.moveToElement(accountDropDownMenu).perform();
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).click().perform();
    }
}