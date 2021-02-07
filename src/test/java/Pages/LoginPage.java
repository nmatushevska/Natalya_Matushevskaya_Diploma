package Pages;

import Utils.PropertyReader;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

@Log4j2
public class LoginPage extends BasePage {
    private final Properties properties = PropertyReader.readFile();
    private final String email = properties.getProperty("email");
    private final String password = properties.getProperty("password");
    private final String wrongPassword = properties.getProperty("wrongPassword");

    @FindBy(xpath = "//a[contains (text(),'Я согласен')]")
    WebElement acceptCookiesButton;

    @FindBy(xpath = "//a[@class='m-header__user']")
    WebElement accountButton;

    @FindBy(xpath = "//a[text()=' Войти ']")
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

    private void acceptCookies() {
        if (accountButton.isDisplayed()) {
            acceptCookiesButton.click();
            log.info("Cookies accepted");
        }
    }

    private void openLoginDialog() {
        acceptCookies();
        actions.moveToElement(accountButton).perform();
        actions.moveToElement(signInButton).click().perform();
        log.info("Login dialog opened");
    }

    @Step("Performing successful login")
    public void successfulLogin() {
        openLoginDialog();
        emailInputField.sendKeys(email);
        log.info("Email entered");
        passwordInputField.sendKeys(password);
        log.info("Password entered");
        logInButton.click();
        log.info("user logged in");
    }

    @Step("Attempting to log in with wrong password")
    public void failedLogin() {
        openLoginDialog();
        emailInputField.sendKeys(email);
        log.info("Email entered");
        passwordInputField.sendKeys(wrongPassword);
        log.info("Wrong password entered");
        logInButton.click();
        log.info("Login attempt with wrong password");
    }

    public String getUserName() {
        return accountName.getAttribute("title");
    }
}