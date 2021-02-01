package Tests;

import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    LoginPage loginPage;

    @Test
    public void successfulLoginTest(){
        loginPage = new LoginPage(driver);

        loginPage.successfulLogin();
        Assert.assertNotNull(loginPage.getUserName());
    }

    @Test
    public void failedLogin(){
        loginPage = new LoginPage(driver);

        loginPage.failedLogin();
        Assert.assertTrue(loginPage.getLogInErrorMessage().isDisplayed());
    }
}