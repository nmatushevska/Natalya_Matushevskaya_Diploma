package pages;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.PropertyReader;

@Log4j2
public class LoginPage extends BasePage {

    @FindBy(xpath = "//a[contains (text(),'Я согласен')]")
    WebElement acceptCookiesButton;

    @FindBy(xpath = "//a[@class='m-header__user']")
    WebElement accountButton;

    @FindBy(xpath = "//a[text()[normalize-space()='Войти']]")
    WebElement signInButton;

    @FindBy(id = "email")
    WebElement emailInputField;

    @FindBy(id = "password")
    WebElement passwordInputField;

    @FindBy(xpath = "//button[text()='Вход']")
    WebElement logInButton;

    @Getter
    @FindBy(xpath = "//p[text()='Введен неверный логин или пароль']")
    WebElement logInErrorMessage;

    @FindBy(xpath = "//div[@class='mh-user__text']")
    WebElement accountName;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String passwordValue() {
        if (System.getenv("password") == null) {
            return PropertyReader.getProperty("password");
        } else {
            return System.getenv("password");
        }
    }

    private String emailValue() {
        if (System.getenv("email") == null) {
            return PropertyReader.getProperty("email");
        } else {
            return System.getenv("email");
        }
    }

    private String wrongPasswordValue() {
        if (System.getenv("wrongPassword") == null) {
            return PropertyReader.getProperty("wrongPassword");
        } else {
            return System.getenv("wrongPassword");
        }
    }

    private String pageUrlValue() {
        if (System.getenv("pageURL") == null) {
            return PropertyReader.getProperty("pageURL");
        } else {
            return System.getenv("pageURL");
        }
    }

    private void openUrl() {
        driver.get(pageUrlValue());
        log.info("Page " + pageUrlValue() + " was opened");
    }

    private void acceptCookies() {
        if (accountButton.isDisplayed()) {
            acceptCookiesButton.click();
            log.info("Cookies accepted");
        }
    }

    private void openLoginDialog() {
        openUrl();
        acceptCookies();
        actions.moveToElement(accountButton).perform();
        actions.moveToElement(signInButton).click().perform();
        log.info("Login dialog opened");
    }

    @Step("Performing successful login")
    public void successfulLogin() {
        openLoginDialog();
        emailInputField.sendKeys(emailValue());
        log.info("Email entered");
        passwordInputField.sendKeys(passwordValue());
        log.info("Password entered");
        logInButton.click();
        log.info("user logged in");
    }

    @Step("Attempting to log in with wrong password")
    public void failedLogin() {
        openLoginDialog();
        emailInputField.sendKeys(emailValue());
        log.info("Email entered");
        passwordInputField.sendKeys(wrongPasswordValue());
        log.info("Wrong password entered");
        logInButton.click();
        log.info("Login attempt with wrong password");
    }

    public String getUserName() {
        return accountName.getAttribute("title");
    }
}