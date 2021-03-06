package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(groups = "one", description = "Successful logging in to the app",priority = 1)
    public void successfulLoginTest() {
        loginPage = new LoginPage(driver);

        loginPage.successfulLogin();
        Assert.assertNotNull(loginPage.getUserName());
    }

    @Test(groups = "two", description = "Failed logging in to the app due to invalid password", priority = 1)
    public void failedLogin() {
        loginPage = new LoginPage(driver);

        loginPage.failedLogin();
        Assert.assertTrue(loginPage.getLogInErrorMessage().isDisplayed());
    }
}