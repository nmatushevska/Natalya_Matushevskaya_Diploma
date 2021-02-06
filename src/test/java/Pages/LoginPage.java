package Pages;

import Utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;


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
    @FindBy(xpath = "//p[text()='Введен неверный логин или пароль']")
    WebElement logInErrorMessage;
    @FindBy(xpath = "//div[@class='mh-user__text']")
    WebElement accountName;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getLogInErrorMessage() {
        return logInErrorMessage;
    }

    private void acceptCookies() {
        if (accountButton.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    private void openLoginDialog() {
        acceptCookies();
        actions.moveToElement(accountButton).perform();
        actions.moveToElement(signInButton).click().perform();
        logger.info("Login dialog opened");
    }

    public void successfulLogin() {
        openLoginDialog();
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        logInButton.click();
    }

    public void failedLogin() {
        openLoginDialog();
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(wrongPassword);
        logInButton.click();
    }

    public String getUserName() {
        return accountName.getAttribute("title");
    }
}